package cn.lncsa.services;

import cn.lncsa.data.model.bulletin.Bulletin;

import java.util.List;

/**
 * Created by catten on 16/7/2.
 */
public interface IBulletinServices {

    /**
     * Save a bulletin
     *
     * @param bulletin
     * @return
     */
    void saveBulletinItem(Bulletin bulletin);

    /**
     * Delete a bulletin item
     *
     * @param bulletinId
     * @return
     */
    void deleteBulletin(Integer bulletinId);

    /*
    *
    * Query methods
    *
    * */

    /**
     * Get single bulletin
     * <p>
     * If not only one bulletin of the specific type, it will only return one
     *
     * @param type
     * @return
     */
    Bulletin getBulletin(String type);

    /**
     * Get all bulletin of specific type
     * <p>
     * If actually items count lesser than count, will return origin List object
     *
     * @param type
     * @param count
     * @return
     */
    List<Bulletin> getBulletinItems(String type, int count);
}
