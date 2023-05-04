package com.example.kunuz2.service;

import com.example.kunuz2.dto.comment.CommentDTO;
import com.example.kunuz2.dto.comment.CommentResponseDTO;
import com.example.kunuz2.dto.profile.ProfileResponseDTO;
import com.example.kunuz2.entity.CommentEntity;
import com.example.kunuz2.exps.ItemNotFoundException;
import com.example.kunuz2.mapper.CommentMapperDTO;
import com.example.kunuz2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;

    // create
    public CommentDTO create(CommentDTO dto, Integer id) {

        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setReplyId(dto.getReplyId());
        entity.setProfileId(id);
        entity.setVisible(true);
        commentRepository.save(entity);
        return dto;
    }

    // update
    public CommentDTO update(CommentDTO dto, Integer id) {
        CommentEntity entity = get(id);
        entity.setContent(dto.getContent());
        entity.setUpdateDate(LocalDateTime.now());
        entity.setArticleId(dto.getArticleId());
        commentRepository.save(entity);
        return dto;
    }

    public CommentEntity get(Integer id) {
        Optional<CommentEntity> byId = commentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("comment not found" + id);
        }
        return byId.get();
    }

    // delete
    public Boolean deleteById(Integer id, Integer prtId) {
        commentRepository.updateVisible(id,prtId);
        return true;
    }

    //getList
    public List<CommentResponseDTO> getByArticleIdCommentList(String id) {
      List<CommentMapperDTO> entityList = commentRepository.findByArticleId(id);
      List<CommentResponseDTO> list = new LinkedList<>();

      entityList.forEach(entity->{
          list.add(toCommentShortInfo(entity));
      });
      return list;
    }

    public CommentResponseDTO toCommentShortInfo(CommentMapperDTO mapperDTO) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(mapperDTO.getId());
        dto.setProfile(new ProfileResponseDTO(mapperDTO.getProfileId(),mapperDTO.getProfileName(),mapperDTO.getProfileSurname()));
        dto.setCreatedDate(mapperDTO.getCreatedDate());
        dto.setUpdateDate(mapperDTO.getUpdateDate());
        return dto;
    }
}
