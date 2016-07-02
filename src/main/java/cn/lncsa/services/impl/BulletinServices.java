package cn.lncsa.services.impl;

import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.Bulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
@Service
public class BulletinServices implements cn.lncsa.services.IBulletinServices {
    public IBulletinDAO bulletinDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    @Override
    public Bulletin getIndexTitleBulletin(){
        return bulletinDAO.getMainPageBulletin();
    }

    @Override
    public Bulletin getAppPageTitleBulletin(){
        return bulletinDAO.getAppGuideBulletin();
    }

    @Override
    public Bulletin getAppGuideBulletin(){
        return bulletinDAO.getAppGuideBulletin();
    }

    @Override
    public Bulletin getIndexBoardBulletin(){
        return bulletinDAO.getMainPageTopBulletin();
    }

    @Override
    public Bulletin getArticleTitleBulletin(){
        return bulletinDAO.getArticleBulletin();
    }

    @Override
    public List<Bulletin> getContactInfoList(){
        return bulletinDAO.getByType("contact_info");
    }

    @Override
    public List<Bulletin> getFriendLinkList(){
        return bulletinDAO.getByType("friend_link");
    }
}
