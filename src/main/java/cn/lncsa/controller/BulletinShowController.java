package cn.lncsa.controller;

import cn.lncsa.data.model.bulletin.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cattenlinger on 16/9/6.
 */
@Controller
@RequestMapping("/bulletins/view")
public class BulletinShowController {

    private IBulletinServices bulletinServices;

    @Autowired
    public void setBulletinServices(IBulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    public Object getBulletin(String typeName){
        return null;
    }

    public Object getBulletin(Integer bulletinId){
        return null;
    }

    public Object getBulletinList(String type){
        return null;
    }
}