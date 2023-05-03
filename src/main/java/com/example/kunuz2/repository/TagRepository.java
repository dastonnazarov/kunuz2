package com.example.kunuz2.repository;

import com.example.kunuz2.entity.TagEntity;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TagRepository extends CrudRepository<TagEntity,String> {
    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
            " FROM article AS a  where  a.type_id =:typeId and status =:status order by created_date desc Limit :limit",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);
}
