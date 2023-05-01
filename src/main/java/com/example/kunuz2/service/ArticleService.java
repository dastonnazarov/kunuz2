package com.example.kunuz2.service;

import com.example.kunuz2.dto.article.ArticleRequestDTO;
import com.example.kunuz2.dto.article.ArticleShortInfoDTO;
import com.example.kunuz2.entity.*;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.exps.ItemNotFoundException;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import com.example.kunuz2.repository.ArticleRepository;
import com.example.kunuz2.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileService profileService;
    private final RegionService regionService;
    private final CategoryService categoryService;
    private final AttachService attachService;

    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {
        // check
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setTypeId(dto.getArticleTypeId());
        articleRepository.save(entity);
        return dto;
    }


    public ArticleRequestDTO update(ArticleRequestDTO dto, String id) {
        ArticleEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);
        return dto;
    }

    public boolean delete(String id) {
//        ArticleEntity entity = get(id);
//        entity.setVisible(Boolean.FALSE);
//        articleRepository.save(entity);
     articleRepository.deleteArticle(id);
        return true;
    }

    public Boolean changeStatus(ArticleStatus status, String id, Integer prtId) {
        ArticleEntity entity = get(id);
        if (status.equals(ArticleStatus.PUBLISHED)) {
            entity.setPublishedDate(LocalDateTime.now());
            entity.setPublisherId(prtId);
        }
        entity.setStatus(status);
        articleRepository.save(entity);
        articleRepository.changeStatus(status, id);
        return true;
    }

    public List<ArticleShortInfoDTO> getLast5ByTypeId(Integer typeId) {
        //1-option

    /*    List<ArticleEntity> entityList = articleRepository.findTop5ByTypeIdAndStatusAndVisibleOrderByCreatedDateDesc(typeId,
                ArticleStatus.NOT_PUBLISHED, true);
                List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;*/


        //2-option

     /*   List<ArticleEntity> entityList = articleRepository.find5ByTypeId(typeId,ArticleStatus.NOT_PUBLISHED);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;*/


        //3 - option
        List<ArticleShortInfoMapper> entityList = articleRepository.find5ByTypeIdNative(typeId, ArticleStatus.NOT_PUBLISHED.name(), 5);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;

    }

    public List<ArticleShortInfoDTO> getLast3ByTypeId(Integer typeId) {
        //1-option

     /*   List<ArticleEntity> list = articleRepository.
                findTop3ByTypeIdAndStatusAndVisibleOrderByCreatedDateDesc(typeId,
                                                                          ArticleStatus.NOT_PUBLISHED,
                                                                    true);
        List<ArticleShortInfoDTO> articleList = new LinkedList<>();
        for(ArticleEntity entity: list){
            articleList.add(toArticleShortInfo(entity));
        }
        return articleList;*/

        //2-option

      /* List<ArticleEntity> articleList = articleRepository.find3ByTypeId(typeId,ArticleStatus.NOT_PUBLISHED);
       List<ArticleShortInfoDTO> list = new LinkedList<>();

        for (ArticleEntity entity : articleList) {
            list.add(toArticleShortInfo(entity));
        }
        return list;*/

        //3-option

        List<ArticleShortInfoMapper> mapperList = articleRepository.find3ByTypeIdNative(typeId,ArticleStatus.NOT_PUBLISHED.name(),3);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            list.add(toArticleShortInfo(mapper));
        }
        return list;
    }
    public ArticleEntity DTOToEntity(ArticleRequestDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setContent(dto.getContent());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public ArticleShortInfoDTO toArticleShortInfo(ArticleEntity entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    public ArticleShortInfoDTO toArticleShortInfo(ArticleShortInfoMapper entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublished_date());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }


}