package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {

    @Query("delete from ArticleTag where articleId = ?1")
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    void deleteAllByArticleId(String articleId);

    @Query("select tagName from ArticleTag where articleId = ?1")
    List<ArticleTag> findTagNamesByArticleId(String articleId);
}
