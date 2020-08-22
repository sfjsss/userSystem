package com.tianyuli.usersystem.rpcDomain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tianyuli.usersystem.pojo.ArticleTag;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArticleResp implements Serializable {
    private static final long serialVersionUID = -5867396188562441627L;

    private String id;
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String title;
    private String content;
    private List<ArticleTag> articleTagList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ArticleTag> getArticleTagList() {
        return articleTagList;
    }

    public void setArticleTagList(List<ArticleTag> articleTagList) {
        this.articleTagList = articleTagList;
    }
}
