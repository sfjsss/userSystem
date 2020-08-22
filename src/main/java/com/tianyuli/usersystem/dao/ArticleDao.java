package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, String> {
}
