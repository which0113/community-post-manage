package com.which.freefish.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import com.which.freefish.common.api.ApiResult;
import com.which.freefish.controller.BaseController;
import com.which.freefish.jwt.JwtUtil;
import com.which.freefish.model.dto.CreateTopicDTO;
import com.which.freefish.model.entity.BmsPost;
import com.which.freefish.model.entity.UmsUser;
import com.which.freefish.model.vo.PostVO;
import com.which.freefish.service.IBmsPostService;
import com.which.freefish.service.IUmsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * post管理
 *
 * @author which
 * @date 2024/05/19
 */
@RestController
@RequestMapping("/admin/post")
@Slf4j
public class PostManageController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @PostMapping("/create")
    public ApiResult<BmsPost> create(@RequestHeader(value = JwtUtil.USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }

    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestBody BmsPost post) {
        post.setModifyTime(new Date());
        String content = post.getContent();
        if (StringUtils.isNotBlank(content)) {
            post.setContent(EmojiParser.parseToAliases(content));
        }
        iBmsPostService.updateById(post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@PathVariable("id") String id) {
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        iBmsPostService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

}
