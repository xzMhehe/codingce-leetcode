package cn.com.codingce.demo.service;

import cn.com.codingce.demo.entity.ScoreEntity;
import cn.com.codingce.demo.utils.PageUtil;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author mxz
 */
public interface ScoreService extends IService<ScoreEntity> {

    PageUtil queryPage(Map<String, Object> params);

    PageUtil queryPageSix(Map<String, Object> params);

    PageUtil queryPageSeven(Map<String, Object> params);

    PageUtil queryPageEight(Map<String, Object> params);
    
}

