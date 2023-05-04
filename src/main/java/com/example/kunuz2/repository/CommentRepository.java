package com.example.kunuz2.repository;

import com.example.kunuz2.entity.CommentEntity;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import com.example.kunuz2.mapper.CommentMapperDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer>, PagingAndSortingRepository<CommentEntity, Integer> {
    @Transactional
    @Modifying
    @Query(" update CommentEntity  set visible = false, profileId=:profileId  where id =:id")
    Integer updateVisible(@Param("id") Integer id, @Param("profileId") Integer profileId);


    @Query(value = " select c.id as id from comment as c inner  join profile as p " +
            " on p.id=c.profile_id where c.article_id=:id ", nativeQuery = true)
    List<CommentMapperDTO> findByArticleId(@Param("id") String id);



}