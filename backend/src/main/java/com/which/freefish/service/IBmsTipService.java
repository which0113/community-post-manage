package com.which.freefish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.which.freefish.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
