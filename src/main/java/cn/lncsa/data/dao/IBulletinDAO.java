package cn.lncsa.data.dao;

import cn.lncsa.data.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface IBulletinDAO extends CrudRepository<Bulletin,Integer> {
    List<Bulletin> getByType(String type);

    @Query("select b from Bulletin b where b.type ='app_guide'")
    Bulletin getAppGuideBulletin();

    @Query("select b from Bulletin b where b.type ='app_info'")
    Bulletin getAppUsageBulletin();

    @Query("select b from Bulletin b where b.type ='main_page'")
    Bulletin getMainPageBulletin();

    @Query("select b from Bulletin b where b.type ='article'")
    Bulletin getArticleBulletin();

    @Query("select b from Bulletin b where b.type ='contact_info'")
    List<Bulletin> getContactInfoList();

    @Query("select b from Bulletin b where b.type ='friend_link'")
    List<Bulletin> getFriendLinkList();
}
