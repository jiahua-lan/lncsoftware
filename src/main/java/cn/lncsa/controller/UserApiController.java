package cn.lncsa.controller;

import cn.lncsa.view.SessionUserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by cattenlinger on 2017/1/9.
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    @RequestMapping("/current")
    public Object selfInfo(HttpSession session){
        return session.getAttribute("session_user");
    }
}
