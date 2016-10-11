package cn.lncsa.data.dao.bulletin;

import cn.lncsa.data.model.bulletin.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by catten on 2016/6/12.
 */
public interface IBulletinDAO extends JpaRepository<Bulletin, Integer> {
    /**
     * Get bulletins directly by type name
     *
     * @param type type name
     * @return a list of bulletin match the type
     */
    @Query("select b from Bulletin b where b.type = ?1 order by b.date")
    List<Bulletin> getByType(String type);
}
