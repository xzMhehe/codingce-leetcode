package cn.com.codingce.demo.conrtoller;

import cn.com.codingce.demo.entity.StudentEntity;
import cn.com.codingce.demo.service.StudentService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author mxz
 */
@RestController
@RequestMapping("demo/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     *
     */
    @RequestMapping(value = "/listfour", method = RequestMethod.GET)
    public ResT listFour() {
        Map<String, Object> params = new HashMap<>();
        PageUtil page = studentService.queryPageFour(params);

        return ResT.ok().put("page", page);
    }
    @RequestMapping(value = "/listfive", method = RequestMethod.GET)
    public ResT listFive() {
        Map<String, Object> params = new HashMap<>();
        PageUtil page = studentService.queryPageFive(params);

        return ResT.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResT list(@RequestParam Map<String, Object> params) {
        PageUtil page = studentService.queryPage(params);

        return ResT.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping(value = "/info/{studentId}", method = RequestMethod.GET)
    public ResT info(@PathVariable("studentId") Integer studentId) {
        StudentEntity student = studentService.getById(studentId);

        return ResT.ok().put("student", student);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResT save(@RequestBody StudentEntity student) {
        studentService.save(student);

        return ResT.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResT update(@RequestBody StudentEntity student) {
        studentService.updateById(student);

        return ResT.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResT delete(@RequestBody Integer[] studentIds) {
        studentService.removeByIds(Arrays.asList(studentIds));

        return ResT.ok();
    }

}
