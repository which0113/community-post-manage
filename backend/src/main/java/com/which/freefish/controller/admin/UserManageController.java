package com.which.freefish.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.which.freefish.common.api.ApiResult;
import com.which.freefish.controller.BaseController;
import com.which.freefish.model.entity.BmsPost;
import com.which.freefish.model.entity.UmsUser;
import com.which.freefish.model.vo.UserVO;
import com.which.freefish.service.IBmsPostService;
import com.which.freefish.service.IUmsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * user管理
 *
 * @author which
 * @date 2024/05/19
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserManageController extends BaseController {

    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IBmsPostService postService;

    @GetMapping("/list")
    public ApiResult<Page<UserVO>> list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<UserVO> list = new Page<>();
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(UmsUser::getModifyTime);
        Page<UmsUser> page = umsUserService.page(new Page<>(pageNo, pageSize), wrapper);
        List<UserVO> collect = page.getRecords().stream().map(umsUser -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(umsUser, userVO);
            return userVO;
        }).collect(Collectors.toList());
        list.setRecords(collect);
        list.setTotal(page.getTotal());
        return ApiResult.success(list);
    }

    @PostMapping(value = "/create")
    public ApiResult<Boolean> create(@RequestBody UmsUser user) {
        return ApiResult.success(umsUserService.save(user));
    }

    @GetMapping(value = "/get")
    public ApiResult<UserVO> create(String id) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(umsUserService.getById(id), userVO);
        return ApiResult.success(userVO);
    }

    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody UmsUser user) {
        user.setModifyTime(new Date());
        return ApiResult.success(umsUserService.updateById(user));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> delete(@PathVariable("id") String id) {
        LambdaQueryWrapper<BmsPost> bmsPostQueryWrapper = new LambdaQueryWrapper<>();
        bmsPostQueryWrapper.eq(BmsPost::getUserId, id);
        postService.remove(bmsPostQueryWrapper);
        return ApiResult.success(umsUserService.removeById(id));
    }

}
