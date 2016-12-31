package cn.lncsa.services;

import cn.lncsa.data.model.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    void save(Bulletin bulletin);

    /**
     * Delete a bulletin item
     *
     * @param bulletinId
     * @return
     */
    void delete(Integer bulletinId);

    /*
    *
    * Query methods
    *
    * */

    /**
     * Get a bulletin by id
     *
     * @param bulletinId
     * @return
     */
    Bulletin get(Integer bulletinId);

    /**
     * Get single bulletin
     * <p>
     * If not only one bulletin of the specific type, it will only return one
     *
     * @param type
     * @return
     */
    Bulletin get(String type);

    /**
     * Get all bulletin of specific type
     * <p>
     * If actually items count lesser than count, will return origin List object
     *
     * @param
     * @param type
     * @return
     */
    Page<Bulletin> get(String type, Pageable pageable);
}
