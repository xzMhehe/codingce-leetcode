package cn.com.codingce.demo.conrtoller;

import cn.com.codingce.demo.entity.ScoreEntity;
import cn.com.codingce.demo.service.ScoreService;
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
@RequestMapping("demo/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResT list(@RequestParam Map<String, Object> params) {
        PageUtil page = scoreService.queryPage(params);

        return ResT.ok().put("page", page);
    }

    @RequestMapping(value = "/listsix", method = RequestMethod.GET)
    public ResT listSix(@RequestParam Map<String, Object> params) {
        PageUtil page = scoreService.queryPageSix(params);

        return ResT.ok().put("page", page);
    }

    @RequestMapping(value = "/listseven", method = RequestMethod.GET)
    public ResT listSeven(@RequestParam Map<String, Object> params) {
        PageUtil page = scoreService.queryPageSeven(params);

        return ResT.ok().put("page", page);
    }

    @RequestMapping(value = "/listeight", method = RequestMethod.GET)
    public ResT listEight(@RequestParam Map<String, Object> params) {
        PageUtil page = scoreService.queryPageEight(params);

        return ResT.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping(value = "/info/{scoreId}", method = RequestMethod.GET)
    public ResT info(@PathVariable("scoreId") Integer scoreId) {
        ScoreEntity score = scoreService.getById(scoreId);

        return ResT.ok().put("score", score);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResT save(@RequestBody ScoreEntity score) {
        scoreService.save(score);

        return ResT.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResT update(@RequestBody ScoreEntity score) {
        scoreService.updateById(score);

        return ResT.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResT delete(@RequestBody Integer[] scoreIds) {
        scoreService.removeByIds(Arrays.asList(scoreIds));

        return ResT.ok();
    }

}
