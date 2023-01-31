package cn.com.codingce.demo.service;

import cn.com.codingce.demo.entity.StudentEntity;
import cn.com.codingce.demo.utils.PageUtil;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author mxz
 */
public interface StudentService extends IService<StudentEntity> {

    PageUtil queryPage(Map<String, Object> params);

    PageUtil queryPageFour(Map<String, Object> params);

    PageUtil queryPageFive(Map<String, Object> params);

}

