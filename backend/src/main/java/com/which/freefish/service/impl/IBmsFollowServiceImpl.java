package com.which.freefish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.which.freefish.mapper.BmsFollowMapper;
import com.which.freefish.model.entity.BmsFollow;
import com.which.freefish.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
