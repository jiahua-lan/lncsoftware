package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.data.model.bulletin.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    // /bulletins/view/item/{string}
    public Object getBulletin(String typeName){
        return new ResultObject(true,bulletinServices.get(typeName));
    }

    // /bulletins/view/item/{int}
    public Object getBulletin(Integer bulletinId){
        return new ResultObject(true,bulletinServices.get(bulletinId));
    }

    // /bulletins/view/list/{string}
    public Object getBulletinList(String type){
        return new ResultObject(true,bulletinServices.get(type));
    }

    // /bulletins/view/all
    // PageRequest
    public Object getAllBulletins(Pageable pageable){
        return null;
    }
}
