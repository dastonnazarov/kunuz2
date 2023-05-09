package com.example.kunuz2.repository;

import com.example.kunuz2.entity.ArticleSaveEntity;
import com.example.kunuz2.mapper.ArticleSaveMapper;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSaveRepository extends CrudRepository<ArticleSaveEntity,Integer>,
        PagingAndSortingRepository<ArticleSaveEntity,Integer> {


    @Modifying
    @Transactional
    @Query(" delete from  ArticleSaveEntity where id=:id")
    int articleSavedDelete(@Param("id") Integer id);



   /* @Modifying
    @Transactional
    @Query("SELECT a.id,a.title,a.description,a.attach_id,a.published_date "+
            " FROM article AS a inner  join attach as at on a.attach_id=at.id where a.id=:id ")
   List<ArticleSaveMapper> getArticleSaveList(@Param("id") Integer id);*/
}
