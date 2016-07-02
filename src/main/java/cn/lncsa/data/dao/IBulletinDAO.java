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
    /**
     * Get bulletins directly by type name
     * Use this if have some bulletins that not using standard bulletin type.
     *
     * @param type type name
     * @return a list of bulletin match the type
     */
    List<Bulletin> getByType(String type);

    @Query("select b from Bulletin b where b.type ='app_guide'")
    Bulletin getAppGuideBulletin();

    @Query("select b from Bulletin b where b.type ='app_info'")
    Bulletin getAppUsageBulletin();

    @Query("select b from Bulletin b where b.type ='main_page'")
    Bulletin getMainPageBulletin();

    @Query("select b from Bulletin b where b.type ='main_page_top'")
    Bulletin getMainPageTopBulletin();

    @Query("select b from Bulletin b where b.type ='article'")
    Bulletin getArticleBulletin();
}
