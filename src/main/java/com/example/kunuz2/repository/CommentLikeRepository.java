package com.example.kunuz2.repository;

import com.example.kunuz2.entity.ArticleLikeEntity;
import com.example.kunuz2.entity.CommentLikeEntity;
;
import com.example.kunuz2.enums.ArticleLikeStatus;
import com.example.kunuz2.enums.CommentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer>, PagingAndSortingRepository<CommentLikeEntity,Integer> {


    Optional<CommentLikeEntity> findByCommentIdAndProfileId(Integer commentId, Integer profileId);


    @Modifying
    @Transactional
    @Query(" update CommentLikeEntity set status =:status where commentId =:commentId and profileId =:profileId")
   int update(@Param("commentId") Integer commentId,
              @Param("profileId") Integer profileId,
              @Param("status") CommentStatus status);


    @Modifying
    @Transactional
    @Query(" delete from CommentLikeEntity where commentId =:commentId and profileId =:profileId")
    int delete(@Param("commentId") Integer commentId,
                @Param("profileId") Integer profileId);
}
