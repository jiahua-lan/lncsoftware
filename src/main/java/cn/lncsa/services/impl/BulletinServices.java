package cn.lncsa.services.impl;

import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
@Service
public class BulletinServices implements IBulletinServices {
    public IBulletinDAO bulletinDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    @Override
    public Bulletin getBulletin(String type) {
        List<Bulletin> bulletins = bulletinDAO.getByType(type);
        return bulletins.get(0);
    }

    @Override
    public List<Bulletin> getBulletinItems(String type, int count) {
        List<Bulletin> bulletins = bulletinDAO.getByType(type);
        if(count > bulletins.size()) return bulletins;
        return bulletins.subList(0,count - 1);
    }
}
