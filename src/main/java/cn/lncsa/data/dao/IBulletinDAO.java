package cn.lncsa.data.dao;

import cn.lncsa.data.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catte on 2016/6/12.
 */
public interface IBulletinDAO extends JpaRepository<Bulletin,Integer> {
}
