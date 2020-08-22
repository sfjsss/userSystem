package com.tianyuli.usersystem.rpcDomain.resp;

import java.io.Serializable;
import java.util.List;

public class UserCenterVOResp implements Serializable {
    private static final long serialVersionUID = 1749781757647255634L;

    private String username;
    private String personalProfile;
    private String provinceAndCity;
    private List<String> userTagList;
    private List<ArticleResp> articleList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getProvinceAndCity() {
        return provinceAndCity;
    }

    public void setProvinceAndCity(String provinceAndCity) {
        this.provinceAndCity = provinceAndCity;
    }

    public List<String> getUserTagList() {
        return userTagList;
    }

    public void setUserTagList(List<String> userTagList) {
        this.userTagList = userTagList;
    }

    public List<ArticleResp> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleResp> articleList) {
        this.articleList = articleList;
    }
}
