package com.which.freefish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.which.freefish.model.dto.CommentDTO;
import com.which.freefish.model.entity.BmsComment;
import com.which.freefish.model.entity.UmsUser;
import com.which.freefish.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
