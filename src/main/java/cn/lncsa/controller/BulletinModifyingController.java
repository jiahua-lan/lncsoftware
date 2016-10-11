package cn.lncsa.controller;

import cn.lncsa.data.model.bulletin.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cattenlinger on 2016/10/6.
 */
@Controller
@RequestMapping("/bulletins/modify")
public class BulletinModifyingController {

    private IBulletinServices bulletinServices;

    @Autowired
    public void setBulletinServices(IBulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    public Object saveBulletin(Bulletin bulletin){
        return null;
    }

    public Object deleteBulletin(Integer bulletinId){
        return null;
    }
}
