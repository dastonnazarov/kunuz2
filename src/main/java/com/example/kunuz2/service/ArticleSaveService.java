package com.example.kunuz2.service;

import com.example.kunuz2.dto.articleSave.ArticleSaveDTO;
import com.example.kunuz2.dto.articleSave.ArticleSaveResponseDTO;
import com.example.kunuz2.entity.ArticleSaveEntity;
import com.example.kunuz2.mapper.ArticleSaveMapper;
import com.example.kunuz2.repository.ArticleSaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleSaveService {
    private final ArticleSaveRepository articleSaveRepository;
    private final  AttachService attachService;


    public ArticleSaveDTO save(ArticleSaveDTO dto,Integer id) {
        ArticleSaveEntity entity = new ArticleSaveEntity();
        entity.setArticle_id(dto.getArticle_id());
        entity.setProfile_id(id);
        entity.setCreated_date(LocalDateTime.now());
        articleSaveRepository.save(entity);
        return dto;
    }

    public Boolean delete(Integer id) {
         articleSaveRepository.articleSavedDelete(id);
        return true;
    }


    /*public List<ArticleSaveResponseDTO> getSaveArticleList(Integer id) {
        List<ArticleSaveMapper> entityList = articleSaveRepository.getArticleSaveList(id);
        List<ArticleSaveResponseDTO> dtoList = new LinkedList<>();

        entityList.forEach(entity -> {
           dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
*/


   /* public ArticleSaveResponseDTO toArticleShortInfo(ArticleSaveMapper entity) {
        ArticleSaveResponseDTO dto = new ArticleSaveResponseDTO();
        dto.setId(entity.getId());
        dto.setArticleEntity(entity.getId(),entity.getTitle(),entity.getDescription());
        dto.setAttach(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }*/
}
