package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
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

    /*
    * Methods
    *
    * */

    // /bulletins/modify/save
    // Bulletin
    public Object saveBulletin(Bulletin bulletin){
        bulletinServices.save(bulletin);
        return new ResultObject(true);
    }

    // /bulletin/modify/delete/{bulletinId}
    public Object deleteBulletin(Integer bulletinId){
        bulletinServices.delete(bulletinId);
        return new ResultObject(true);
    }
}
