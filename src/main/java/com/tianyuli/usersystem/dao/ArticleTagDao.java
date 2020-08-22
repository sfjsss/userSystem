package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {
}
