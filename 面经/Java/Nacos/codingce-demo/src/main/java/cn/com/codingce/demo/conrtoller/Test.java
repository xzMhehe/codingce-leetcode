package cn.com.codingce.demo.conrtoller;

import cn.com.codingce.demo.utils.ResT;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("test")
public class Test {


    /**
     * 通过NacosValue读取配置，
     * autoRefreshed 表示是否自动更新
     */
    @NacosValue(value = "${my.http_url}", autoRefreshed = true)
    private String httpUrl;

    /**
     * 信息
     */
    @RequestMapping(value = "/a", method = GET)
    public ResT info() {
        return ResT.ok().put("nacos", httpUrl);
    }

    /**
     * 信息
     */
    @RequestMapping(value = "/name", method = GET, produces = "application/json;charset=UTF-8")
    public String name() {
        return httpUrl;
    }

}
