package cn.lncsa.data.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by catten on 10/11/16.
 */
public interface IPasswordDAO extends JpaRepository<String,Integer> {

    @Query(value = "select password from users where id = ?1",nativeQuery = true)
    String get(Integer userId);
}
