package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.ArticleDao;
import com.tianyuli.usersystem.dao.ArticleTagDao;
import com.tianyuli.usersystem.pojo.Article;
import com.tianyuli.usersystem.pojo.ArticleTag;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.utils.UUIDUtils;
import com.tianyuli.usersystem.rpcDomain.req.ArticleRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

        return null;
    }
}
