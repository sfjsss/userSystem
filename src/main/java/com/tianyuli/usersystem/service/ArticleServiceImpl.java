package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.ArticleDao;
import com.tianyuli.usersystem.dao.ArticleTagDao;
import com.tianyuli.usersystem.pojo.Article;
import com.tianyuli.usersystem.pojo.ArticleTag;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.utils.UUIDUtils;
import com.tianyuli.usersystem.rpcDomain.req.ArticleRequest;
import com.tianyuli.usersystem.rpcDomain.resp.ArticleResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.articleDao;
    }

    public ArticleServiceImpl() {
    }

    @Override
    public RespResult publish(String userId, ArticleRequest articleRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(articleRequest, article);
        article.setId(UUIDUtils.get());
        article.setUserId(userId);
        article.setPublishTime(new Timestamp(articleRequest.getPublishTime().getTime()));
        articleDao.save(article);

        ArticleTag articleTag = new ArticleTag();
        for (String tag : articleRequest.getArticleTagList()) {
            articleTag.setTagName(tag);
            articleTag.setId(UUIDUtils.get());
            articleTag.setArticleId(article.getId());
            articleTagDao.save(articleTag);
        }

        return new RespResult(ResultCode.SUCCESS, article.getId());
    }

    @Override
    public RespResult deleteArticleById(String id) {
        if (StringUtils.isBlank(id)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "article id cannot be blank");
        } else if (!articleDao.findById(id).isPresent()) {
            return new RespResult(ResultCode.RESULT_DATA_NONE, "article does not exist");
        }
        articleDao.deleteById(id);
        articleTagDao.deleteAllByArticleId(id);

        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public List<Article> getRecentArticles() {
        Pageable pageable = (Pageable) PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "publishTime"));
        ArrayList<Article> articles = new ArrayList<>();
        articleDao.findAll((org.springframework.data.domain.Pageable) pageable).forEach(articles::add);

        return articles;
    }

    @Override
    public RespResult showSingleArticle(String id) {
        if (StringUtils.isBlank(id)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "article id cannot be blank");
        }
        Optional<Article> optionalArticle = articleDao.findById(id);
        if (optionalArticle.isPresent()) {
            List<ArticleTag> articleTagList = articleTagDao.findTagNamesByArticleId(id);
            ArticleResp articleResp = new ArticleResp();
            articleResp.setArticleTagList(articleTagList);
            BeanUtils.copyProperties(optionalArticle.get(), articleResp);

            return new RespResult(ResultCode.SUCCESS, articleResp);
        }
        return new RespResult(ResultCode.RESULT_DATA_NONE);
    }

    @Override
    public RespResult updateArticleByAuthor(String userId, ArticleRequest articleRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(articleRequest, article);
        article.setPublishTime(new Timestamp(articleRequest.getPublishTime().getTime()));
        articleTagDao.deleteAllByArticleId(article.getId());

        ArticleTag articleTag = new ArticleTag();
        for (String tag : articleRequest.getArticleTagList()) {
            articleTag.setTagName(tag);
            articleTag.setId(UUIDUtils.get());
            articleTag.setArticleId(article.getId());
            articleTagDao.save(articleTag);
        }
        articleDao.save(article);
        return new RespResult(ResultCode.SUCCESS);
    }
}
