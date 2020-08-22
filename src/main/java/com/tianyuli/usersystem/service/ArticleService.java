package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.Article;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.ArticleRequest;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService extends BaseService<Article, String> {
    RespResult publish(String userId, ArticleRequest articleRequest);
}
