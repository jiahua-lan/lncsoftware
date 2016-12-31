package cn.lncsa.controller;

import cn.lncsa.data.model.Bulletin;
import cn.lncsa.data.model.User;
import cn.lncsa.services.BulletinServices;
import cn.lncsa.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by cattenlinger on 2017/1/1.
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    private BulletinServices bulletinServices;

    private UserServices userServices;

    @Autowired
    private void setBulletinServices(BulletinServices bulletinServices){
        this.bulletinServices = bulletinServices;
    }

    @Autowired
    private void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping("/")
    public String bulletins(@RequestParam(value = "page",defaultValue = "0") int page, Model model){
        model.addAttribute("bulletins",bulletinServices.get(new PageRequest(page,10)));
        model.addAttribute("tags",bulletinServices.getAllTags());
        return "bulletins";
    }

    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String write(){
        return "writeBulletin";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@ModelAttribute @Valid Bulletin bulletin, BindingResult result, Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("session_userid");
        if(userid == null) return "redirect:/user/login";

        if(result.hasErrors()) return "dialogs/bulletinSaveFailed";

        User user = userServices.get(userid);
        bulletin.setAuthor(user);
        bulletin.setDate(new Date());
        bulletinServices.save(bulletin);

        model.addAttribute("bulletin",bulletin);

        return "dialogs/bulletinSaved";
    }
}
