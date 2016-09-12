package cn.lncsa.services;

import cn.lncsa.data.model.Bulletin;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
public interface IBulletinServices {

    /**
     *
     * Get single bulletin
     *
     * If not only one bulletin of the specific type, it will only return one
     *
     * @param type
     * @return
     */
    Bulletin getBulletin(String type);

    /**
     *
     * Get all bulletin of specific type
     *
     * If actually items count lesser than count, will return origin List object
     *
     * @param type
     * @param count
     * @return
     */
    List<Bulletin> getBulletinItems(String type, int count);
}
