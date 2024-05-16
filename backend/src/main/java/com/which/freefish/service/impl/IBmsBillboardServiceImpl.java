package com.which.freefish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.which.freefish.mapper.BmsBillboardMapper;
import com.which.freefish.model.entity.BmsBillboard;
import com.which.freefish.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
