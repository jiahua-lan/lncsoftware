package cn.lncsa.controller;

import cn.lncsa.data.model.Topic;
import cn.lncsa.data.model.User;
import cn.lncsa.services.TopicServices;
import cn.lncsa.services.UserServices;
import cn.lncsa.view.SessionUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by cattenlinger on 2017/1/7.
 */
@Controller
@RequestMapping("/topic")
public class TopicController {

    private TopicServices topicServices;
    private UserServices userServices;

    @Autowired
    private void setTopicServices(TopicServices topicServices){
        this.topicServices = topicServices;
    }

    @Autowired
    private void setUserServices(UserServices userServices){
        this.userServices = userServices;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(){
        return "createTopic";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Topic topic, Model model, HttpSession session){
        SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("session_user");
        if(sessionUserBean == null) return "redirect:/user/login";

        if(topicServices.hasTopic(topic.getTitle())) return "dialogs/topicCreateFailed";

        topic.setCreateDate(new Date());
        topic.setCreator(userServices.get(sessionUserBean.getUserId()));
        topic.setWeight(1000);

        topicServices.save(topic);
        model.addAttribute("topic_title",topic.getTitle());
        return "dialogs/topicCreateSuccess";
    }
}
