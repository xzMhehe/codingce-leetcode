package cn.com.codingce.demo.service;

import cn.com.codingce.demo.utils.PageUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.demo.entity.ClassEntity;

import java.util.Map;

/**
 * @author mxz
 */
public interface ClassService extends IService<ClassEntity> {

    PageUtil queryPage(Map<String, Object> params);

}

