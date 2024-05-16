package com.which.freefish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.which.freefish.mapper.BmsTipMapper;
import com.which.freefish.model.entity.BmsTip;
import com.which.freefish.service.IBmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper
        , BmsTip> implements IBmsTipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
