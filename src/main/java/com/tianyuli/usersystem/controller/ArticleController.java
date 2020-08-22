package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import com.tianyuli.usersystem.rpcDomain.req.ArticleRequest;
import com.tianyuli.usersystem.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult publish(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String authHeader,
                              @RequestBody ArticleRequest articleRequest) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(authHeader);
        return articleService.publish(userId, articleRequest);
    }
}
