package cn.lncsa.controller;

import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.Bulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cattenlinger on 16/9/6.
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    private IBulletinDAO bulletinDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    /**
     * Get single bulletin
     *
     * If there has more than one bulletin item, will return the first item in the list.
     *
     * @param type type of bulletin
     * @return
     */
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public @ResponseBody Bulletin getBulletin(@RequestParam("type") String type){
        List<Bulletin> bulletins = bulletinDAO.getByType(type);
        if(bulletins == null) return null;
        return bulletins.get(0);
    }

    /**
     * Get a list of bulletin items
     *
     * @param type type of items that want to get
     * @param count how much, if count > type.count, will return all items.
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Bulletin> getBulletinList(@RequestParam("type") String type, @RequestParam("count") int count){
        List<Bulletin> bulletins = bulletinDAO.getByType(type);
        if(count > bulletins.size()) return bulletins;
        else return bulletins.subList(0,count - 1);
    }
}
