package cn.iocoder.yudao.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
public class SpaController {
    /**
     * 允许前端静态页面url绕过拦截器，交由react-router处理
     * @return
     */
    @RequestMapping(value={"/signin", "/orderList", "/salesSummary", "/scheduleList"})
    @PermitAll
    public String redirectToIndex(){
        return "forward:/index.html";
    }
}
