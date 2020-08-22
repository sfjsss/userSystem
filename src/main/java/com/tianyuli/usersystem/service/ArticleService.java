package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.Article;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.ArticleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService extends BaseService<Article, String> {
    RespResult publish(String userId, ArticleRequest articleRequest);

    RespResult updateArticleByAuthor(String userId, ArticleRequest articleRequest);

    RespResult deleteArticleById(String id);

    RespResult showSingleArticle(String id);

    List<Article> getRecentArticles();
}
