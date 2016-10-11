package cn.lncsa.services.impl;

import cn.lncsa.data.dao.bulletin.IBulletinDAO;
import cn.lncsa.data.model.bulletin.Bulletin;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
@Service
public class BulletinServices implements IBulletinServices {
    private IBulletinDAO bulletinDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    @Override
    public void save(Bulletin bulletin) {
        bulletinDAO.save(bulletin);
    }

    @Override
    public void delete(Integer bulletinId) {
        bulletinDAO.delete(bulletinId);
    }

    @Override
    public Bulletin get(Integer bulletinId) {
        return bulletinDAO.findOne(bulletinId);
    }

    @Override
    public Bulletin get(String type) {
        List<Bulletin> bulletins = bulletinDAO.getByType(type);
        return bulletins.size() > 0 ? bulletins.get(0) : null;
    }

    @Override
    public Page<Bulletin> get(String type, Pageable pageable) {
        return bulletinDAO.getByType(type,pageable);
    }
}
