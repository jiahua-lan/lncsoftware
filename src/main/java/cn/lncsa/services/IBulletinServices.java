package cn.lncsa.services;

import cn.lncsa.data.model.Bulletin;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
public interface IBulletinServices {
    Bulletin getIndexTitleBulletin();

    Bulletin getAppPageTitleBulletin();

    Bulletin getAppGuideBulletin();

    Bulletin getIndexBoardBulletin();

    Bulletin getArticleTitleBulletin();

    List<Bulletin> getContactInfoList();

    List<Bulletin> getFriendLinkList();
}
