package com.example.kunuz2.dto.articleSave;

import com.example.kunuz2.dto.attach.AttachDTO;
import com.example.kunuz2.entity.ArticleEntity;
import com.example.kunuz2.entity.ArticleSaveEntity;
import com.example.kunuz2.entity.AttachEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ArticleSaveResponseDTO {
    private Integer id;
    private ArticleEntity articleEntity;
    private AttachDTO attach;

    public void setArticleEntity(String aId, String title, String description) {
    }
}
