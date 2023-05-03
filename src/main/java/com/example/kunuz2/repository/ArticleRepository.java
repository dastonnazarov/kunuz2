package com.example.kunuz2.repository;

import com.example.kunuz2.entity.ArticleEntity;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.mapper.ArticleFullInfoMapper;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {

    Optional<ArticleEntity> findByTitle(String title);


    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set visible = false where id =:id")
    int deleteArticle(@Param("id") String articleId);

    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set status = :status where id =:id")
    int changeStatus(@Param("status") ArticleStatus status, @Param("id") String id);

    // 5 - option
/*    List<ArticleEntity> findTop5ByTypeIdAndStatusAndVisibleOrderByCreatedDateDesc(Integer typeId,
                                                                                  ArticleStatus status,
                                                                                  Boolean visible);

    @Query("SELECT new ArticleEntity(id,title,description,attachId,publishedDate) From ArticleEntity
    where status =:status and visible = true and typeId =:typeId order by createdDate desc limit 5")
    List<ArticleEntity> find5ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);*/

    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
            " FROM article AS a  where  a.type_id =:typeId and status =:status order by created_date desc Limit :limit",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);


/*    List<ArticleEntity> findTop3ByTypeIdAndStatusAndVisibleOrderByCreatedDateDesc(Integer typeId,
                                                                                  ArticleStatus status,
                                                                                  Boolean visible);

    @Query(value = "select new ArticleEntity(id,title,description,attachId,publishedDate) from ArticleEntity " +
            "where status=:status and visible = true and typeId=:typeId order by createdDate desc limit 3 ")
    List<ArticleEntity> find3ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);*/


    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
            " FROM article AS a  where a.type_id=:typeId and status =:status order by  created_date desc Limit :limit", nativeQuery = true)
    List<ArticleShortInfoMapper> find3ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") int limit);

    //7
//    @Query("select new ArticeEntity(id,title,description,attachId,publishedDate) " +
//            " Where  status =:status and visible = true and id not in :idList " +
//            " order by createDate desc limit 8")
//    List<ArticleShortInfoMapper> find8ByArticleIdNative(@Param("gId") List<String> idList,
//                                                        @Param("status") ArticleStatus status);

    @Query("SELECT new ArticleEntity(id,title,description,attachId,publishedDate) From ArticleEntity " +
            "where status =:status and visible = true and id not in :idList " +
            " order by createdDate desc limit 8")
    List<ArticleEntity> find8ByArticleIdNative(@Param("status") ArticleStatus status,
                                                   @Param("idList") List<String> idList);

    //8




    //9
    @Query(value = " select  a.id,a.title,a.description,a.attach_id,a.published_date ,a.created_date " +
            " from article as a inner join  article_type as t on t.id=a.type_id where a.id != :id  order by a.created_date desc limit :limit ", nativeQuery = true)
    List<ArticleShortInfoMapper> find4ByIdTypeByIdNative(@Param("id") String id,
                                                         @Param("limit") int limit);


    //10
    @Query(value = " select a.id,a.title,a.description,a.attach_id,a.published_date " +
            " from article as a order by a.view_count desc  limit :limit ", nativeQuery = true)
    List<ArticleShortInfoMapper> find4MostRead(@Param("limit") int limit);

    //11
    @Query(value = " select  a.id,a.tilte,a.description,a.attach_id,a.published_date "+
   " from article as a inner  join tag as t on t.id = a.tag_id where a.tag_id=:id " +
            " order by  a.creaded_date desc limit :limit ",
            nativeQuery = true)
    List<ArticleShortInfoMapper>find4LastByTagId(@Param("id") Integer id,
                                                 @Param("limit") Integer limit);


    //12
    @Query("from ArticleEntity where typeId =:typeId and regionId =:regionId order by createdDate limit 5")
    List<ArticleEntity> get5ByTypeAndReg(@Param("typeId") Integer typeId, @Param("regionId") Integer regionId);


    //13
    Page<ArticleEntity> findAllByRegionId(Pageable paging, Integer id);


    //14
    @Query(value = "from ArticleEntity where categoryId =:categoryId order by createdDate limit 5")
    List<ArticleEntity> get5ByCategoryId(@Param("categoryId") Integer categoryId);


    //15
    Page<ArticleEntity> findAllByCategoryId(Pageable pageable, Integer id);


    // TODO finish 11 - task
    @Query(value = "select a.id         as articleId, " +
            "       a.attach_id         as attachId, " +
            "       a.category_id       as categoryId, " +
            "       a.content           as content, " +
            "       a.title             as title, " +
            "       a.description       as description, " +
            "       a.created_date      as createdDate, " +
            "       a.moderator_id      as moderatorId, " +
            "       a.region_id         as regionId, " +
            "       (case :lang when 'uz' then r.name_uz when 'en' then r.name_eng else r.name_ru end) as regionName, " +
            "       a.category_id                                                                     as categoryId, " +
            "       (case :lang when 'uz' then c.name_uz when 'en' then c.name_eng else c.name_ru end) as categoryName " +
            "     from article as a " +
            "         inner join region as r on r.id = a.region_id " +
            "         inner join category as c on c.id = a.category_id", nativeQuery = true)
    ArticleFullInfoMapper findIdAndLangNative(@Param("lang") String lang);

}
