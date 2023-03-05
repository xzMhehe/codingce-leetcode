package cn.com.codingce.demo.conrtoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String helloThymeleaf(Model model) {
        return "index";
    }

}
