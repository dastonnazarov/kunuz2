package com.example.kunuz2.service;


import com.example.kunuz2.entity.ArticleLikeEntity;
import com.example.kunuz2.entity.CommentLikeEntity;
import com.example.kunuz2.enums.ArticleLikeStatus;
import com.example.kunuz2.enums.CommentStatus;
import com.example.kunuz2.repository.CommentLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    public Boolean like(Integer commentId,Integer profileId){
        makeEmotion(commentId,profileId, CommentStatus.LIKE);
        return true;
    }
    public Boolean disLike(Integer commentId,Integer profileId){
        makeEmotion(commentId,profileId, CommentStatus.DISLIKE);
        return true;
    }


    public Boolean delete(Integer commentId,Integer profileId){
        commentLikeRepository.delete(commentId,profileId);
        return true;
    }
    public void makeEmotion(Integer commentId,Integer profileId,CommentStatus status){
        Optional<CommentLikeEntity> optional = commentLikeRepository.findByCommentIdAndProfileId(commentId, profileId);
        CommentLikeEntity entity = new CommentLikeEntity();
        if(optional.isEmpty()){
            entity.setCommentId(commentId);
            entity.setProfileId(profileId);
            entity.setStatus(status);
            commentLikeRepository.save(entity);
        }else {
            commentLikeRepository.update(commentId,profileId,status);
        }
    }
}
