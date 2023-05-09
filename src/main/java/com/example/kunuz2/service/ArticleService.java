package com.example.kunuz2.service;

import com.example.kunuz2.dto.article.ArticleFilterDTO;
import com.example.kunuz2.dto.article.ArticleFullInfoDTO;
import com.example.kunuz2.dto.article.ArticleRequestDTO;
import com.example.kunuz2.dto.article.ArticleShortInfoDTO;
import com.example.kunuz2.entity.*;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.enums.LangEnum;
import com.example.kunuz2.exps.AppBadRequestException;
import com.example.kunuz2.exps.ItemNotFoundException;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import com.example.kunuz2.repository.ArticleCustomRepository;
import com.example.kunuz2.repository.ArticleRepository;
import com.example.kunuz2.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final AttachService attachService;
    private final CategoryService categoryService;
    private final RegionService regionService;
    private final ArticleTypeService articleTypeService;
    private final ArticleCustomRepository articleCustomRepository;
    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {

        //getTitle(dto.getTitle());
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setTypeId(dto.getArticleTypeId());
        entity.setViewCount(dto.getViewCount());
        entity.setTagId(dto.getTagId());
        entity.setPublisherId(dto.getPublishedId());
        entity.setPublishedDate(LocalDateTime.now());
        articleRepository.save(entity);
        dto.setId(entity.getId());
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
        entity.setTagId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);
        return dto;
    }

    public boolean delete(String id) {
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

    public ArticleFullInfoDTO toFullDTO(ArticleEntity entity, LangEnum langEnum) {
        ArticleFullInfoDTO dto = new ArticleFullInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setViewCount(entity.getViewCount());
        dto.setAttach(attachService.getAttachLink(entity.getAttachId()));
        dto.setCategory(categoryService.getByIdAndLang(entity.getCategoryId(), langEnum));
        dto.setRegion(regionService.getByIdAndLang(entity.getCategoryId(), langEnum));
        dto.setArticleType(articleTypeService.getByIdAndLang(entity.getCategoryId(), langEnum));
        // tag_list
        return dto;
    }


    public ArticleEntity DTOToEntity(ArticleRequestDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setContent(dto.getContent());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        entity.setDescription(dto.getDescription());
        return entity;
    }

    //2
    public ArticleShortInfoDTO toArticleShortInfo(ArticleEntity entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    //3
    public ArticleShortInfoDTO toArticleShortInfo(ArticleShortInfoMapper entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublished_date());
        dto.setImage(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    //4
    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }


    //5
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
        List<ArticleShortInfoMapper> entityList = articleRepository.find5ByTypeIdNative(typeId, ArticleStatus.PUBLISHED.name(), 5);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;

    }

    //6
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
      /*  List<ArticleEntity> articleList = articleRepository.find3ByTypeId(typeId, ArticleStatus.NOT_PUBLISHED);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleEntity entity : articleList) {
            list.add(toArticleShortInfo(entity));
        }
        return list;*/
        //3-option

        List<ArticleShortInfoMapper> mapperList = articleRepository.find3ByTypeIdNative(typeId, ArticleStatus.NOT_PUBLISHED.name(), 3);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            list.add(toArticleShortInfo(mapper));
        }
        return list;

    }

    //7
    public List<ArticleShortInfoDTO> getLast8notGivenList(List<String> idList) {
        //1-option
       /* List<ArticleEntity> list = articleRepository.getAllArticle(ArticleStatus.NOT_PUBLISHED);
        List<ArticleEntity> entityList = new LinkedList<>();
        List<ArticleShortInfoDTO> infoDTOS = new LinkedList<>();
        for (String s : gId) {
            for (ArticleEntity entity : list) {
                if (!s.equals(entity.getId())) {
                    entityList.add(entity);
                } else {
                    break;
                }
                if (entityList.size() == 8) break;
            }
            if (entityList.size() == 8) break;
        }

        entityList.forEach(entity -> {
            infoDTOS.add(toArticleShortInfo(entity));
        });
        return infoDTOS;*/
        //2-option
        List<ArticleEntity> articleList = articleRepository.find8ByArticleIdNative(ArticleStatus.NOT_PUBLISHED, idList);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleEntity entity : articleList) {
            list.add(toArticleShortInfo(entity));
        }
        return list;

        //3-option
    /*    List<ArticleShortInfoMapper> mapperList = articleRepository.find8ByArticleIdNative(gId, ArticleStatus.NOT_PUBLISHED.name(), 8);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            list.add(toArticleShortInfo(mapper));
        }
        return list;*/
    }

    //8
    public ArticleFullInfoDTO getById(String id,LangEnum langEnum) {
        ArticleEntity entity = get(id);
        if (!entity.getVisible() || !entity.getStatus().equals(ArticleStatus.NOT_PUBLISHED)) {
            throw new AppBadRequestException("wrong article status !");
        }
        return toFullDTO(entity,langEnum);
    }


    //9
    public List<ArticleShortInfoDTO> find4ByIdTypeByIdNative(String id) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.find4ByIdTypeByIdNative(id, 4);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            list.add(toArticleShortInfo(mapper));
        }
        return list;
    }

    //10
    public List<ArticleShortInfoDTO> get4MostReadArticle() {
        List<ArticleShortInfoMapper> mapperList = articleRepository.find4MostRead(4);
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            list.add(toArticleShortInfo(mapper));
        }
        return list;
    }

    //11find4LastByTagId
    public List<ArticleShortInfoDTO> find4LastByTagId(Integer id) {
        List<ArticleShortInfoMapper> listOfArticle = articleRepository.find4LastByTagId(id, 4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        listOfArticle.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }

    //12
    public List<ArticleShortInfoDTO> get5ByTypeAndReg(Integer typeId, Integer regionId) {
        List<ArticleEntity> listOfArticle = articleRepository.get5ByTypeAndReg(typeId, regionId);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        listOfArticle.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }


    //13
    public Page<ArticleShortInfoDTO> getArticleByRegionIdPaging(int page, int size, Integer id) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ArticleEntity> pageObj = articleRepository.findAllByRegionId(paging, id);

        Long totalCount = pageObj.getTotalElements();

        List<ArticleEntity> entityList = pageObj.getContent();
        List<ArticleShortInfoDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            list.add(toArticleShortInfo(entity));
        });
        return new PageImpl<>(list, paging, totalCount);
    }


    //14
    public List<ArticleShortInfoDTO> get5ByCategory(Integer categoryId) {
        List<ArticleEntity> listOfArticle = articleRepository.get5ByCategoryId(categoryId);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        listOfArticle.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }


    //15
    public Page<ArticleShortInfoDTO> getArticleByCategoryIdPaging(int page, int size, Integer id) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ArticleEntity> pagingArticle = articleRepository.findAllByCategoryId(pageable, id);

        long totalElements = pagingArticle.getTotalElements();
        List<ArticleEntity> entityList = pagingArticle.getContent();
        List<ArticleShortInfoDTO> list = new LinkedList<>();

        entityList.forEach(content -> {
            list.add(toArticleShortInfo(content));
        });
        return new PageImpl<>(list, pageable, totalElements);
    }



    //18
    public PageImpl<ArticleShortInfoDTO> filter(ArticleFilterDTO filterDTO, int page, int size) {
        Page<ArticleEntity> pageObj = articleCustomRepository.filter(filterDTO, page, size);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        pageObj.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return new PageImpl<>(dtoList, PageRequest.of(page, size), pageObj.getTotalElements());
    }

}