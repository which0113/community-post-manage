package com.which.freefish.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户vo
 *
 * @author which
 * @date 2024/05/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 3L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 别名
     */
    private String alias;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 生物
     */
    private String bio;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 令牌
     */
    private String token;

    /**
     * 活动
     */
    private Boolean active;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
