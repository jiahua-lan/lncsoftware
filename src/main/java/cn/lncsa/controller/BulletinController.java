package cn.lncsa.controller;

import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cattenlinger on 16/9/6.
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    private IBulletinServices bulletinServices;

    @Autowired
    public void setBulletinServices(IBulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    /**
     * Get single bulletin
     *
     * If there has more than one bulletin item, will return the first item in the list.
     *
     * @param type type of bulletin
     * @return
     */
    @RequestMapping(value = "/single/{type}", method = RequestMethod.GET)
    public @ResponseBody Bulletin getBulletin(@PathVariable("type") String type){
        return bulletinServices.getBulletin(type);
    }

    /**
     * Get a list of bulletin items
     *
     * @param type type of items that want to get
     * @param count how much, if count > type.count, will return all items.
     * @return
     */
    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
    public @ResponseBody List<Bulletin> getBulletinList(@PathVariable("type") String type, @RequestParam("count") int count){
        return bulletinServices.getBulletinItems(type,count);
    }
}
