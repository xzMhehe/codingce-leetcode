package cn.com.codingce.demo.conrtoller;

import cn.com.codingce.demo.entity.ClassEntity;
import cn.com.codingce.demo.service.ClassService;
import cn.com.codingce.demo.utils.PageUtil;
import cn.com.codingce.demo.utils.ResT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @author mxz
 */
@RestController
@RequestMapping("demo/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResT list(@RequestParam Map<String, Object> params) {
        PageUtil page = classService.queryPage(params);

        return ResT.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{classId}", method = RequestMethod.GET)
    public ResT info(@PathVariable("classId") Integer classId) {
        ClassEntity clazz = classService.getById(classId);

        return ResT.ok().put("clazz", clazz);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResT save(@RequestBody ClassEntity clazz) {
        classService.save(clazz);

        return ResT.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResT update(@RequestBody ClassEntity clazz) {
        classService.updateById(clazz);

        return ResT.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResT delete(@RequestBody Integer[] classIds) {
        classService.removeByIds(Arrays.asList(classIds));

        return ResT.ok();
    }

}
