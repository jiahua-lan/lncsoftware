package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.permissions.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catten on 16/6/12.
 */
public interface IRoleDAO extends JpaRepository<Role, Integer> {

    Role getByName(String name);

    Role getById(Integer id);

}
