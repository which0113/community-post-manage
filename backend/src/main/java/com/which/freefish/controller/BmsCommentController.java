package com.which.freefish.controller;

import com.which.freefish.common.api.ApiResult;
import com.which.freefish.jwt.JwtUtil;
import com.which.freefish.model.dto.CommentDTO;
import com.which.freefish.model.entity.BmsComment;
import com.which.freefish.model.entity.UmsUser;
import com.which.freefish.model.vo.CommentVO;
import com.which.freefish.service.IBmsCommentService;
import com.which.freefish.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

    @Resource
    private IBmsCommentService bmsCommentService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }

    @PostMapping("/add_comment")
    public ApiResult<BmsComment> add_comment(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                             @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
