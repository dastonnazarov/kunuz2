package com.example.kunuz2.repository;


import com.example.kunuz2.entity.ArticleLikeEntity;
import com.example.kunuz2.enums.ArticleLikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity, Integer> {


    Optional<ArticleLikeEntity> findByArticleIdAndProfileId(String articleId, Integer profileId);

    @Modifying
    @Transactional
    @Query("update ArticleLikeEntity  set status =:status where articleId=:articleId and profileId=:profileId")
    int update(@Param("status") ArticleLikeStatus status,
               @Param("articleId") String articleId,
               @Param("profileId") Integer profileId);


    @Modifying
    @Transactional
    @Query("delete from ArticleLikeEntity where articleId=:articleId and profileId=:profileId")
    int deletes(@Param("articleId") String articleId,
                @Param("profileId") Integer profileId);


}

