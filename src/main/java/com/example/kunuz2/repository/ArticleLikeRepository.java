package com.example.kunuz2.repository;


import com.example.kunuz2.entity.ArticleLikeEntity;
import com.example.kunuz2.enums.ArticleLikeStatus;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity,Integer> {


    Optional<ArticleLikeEntity> findByArticleIdAndProfileId(String article_id, Integer profile_id);

    @Transactional
    @Modifying
    @Query(" update ArticleLikeEntity set status =:status where  articleId =:article_id and profilId =:profile_id")
    int update(@Param("article_id") String article_id,
               @Param("profile_id") Integer profile_id,
               @Param("status") ArticleLikeStatus status);


    @Transactional
    @Modifying
    @Query(" delete from ArticleLikeEntity  where  articleId =:article_id and profilId =:profile_id")
  int  removeLikeOrDislike(@Param("article_id") String article_id,
               @Param("profile_id") Integer profile_id);


}

