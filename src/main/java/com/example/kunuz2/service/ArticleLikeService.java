package com.example.kunuz2.service;

import com.example.kunuz2.dto.articeLike.ArticleLikeDTO;
import com.example.kunuz2.entity.ArticleLikeEntity;
import com.example.kunuz2.enums.ArticleLikeStatus;
import com.example.kunuz2.repository.ArticleLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleLikeService {
      private final ArticleLikeRepository articleLikeRepository;

      public Boolean like(String articleId,Integer profileId){
             makeEmotion(articleId,profileId,ArticleLikeStatus.LIKE);
             return true;
      }
    public Boolean disLike(String articleId,Integer profileId){
        makeEmotion(articleId,profileId,ArticleLikeStatus.DISLIKE);
        return true;
    }


    public Boolean delete(String articleId,Integer profileId){
        articleLikeRepository.removeLikeOrDislike(articleId,profileId);
        return true;
    }
      public void makeEmotion(String articleId,Integer profileId,ArticleLikeStatus status){
          Optional<ArticleLikeEntity>  optional= articleLikeRepository.findByArticleIdAndProfileId(articleId,profileId);
          ArticleLikeEntity entity = new ArticleLikeEntity();
          if(optional.isEmpty()){
              entity.setArticleId(articleId);
              entity.setProfileId(profileId);
              entity.setStatus(status);
              articleLikeRepository.save(entity);
          }else {
              articleLikeRepository.update(articleId,profileId,status);
          }
      }


}
