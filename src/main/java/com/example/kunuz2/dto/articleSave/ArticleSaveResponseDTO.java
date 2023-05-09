package com.example.kunuz2.dto.articleSave;

import com.example.kunuz2.dto.attach.AttachDTO;
import com.example.kunuz2.entity.ArticleEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ArticleSaveResponseDTO {
    private String id;
    private ArticleEntity articleEntity;
    private AttachDTO attach;

    public void setArticleEntity(String aId, String title, String description) {
    }
}
