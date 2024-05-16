package com.which.freefish.model.vo;

import lombok.Data;

import java.util.Date;


@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private String username;

    private String alias;

    private Date createTime;

}
