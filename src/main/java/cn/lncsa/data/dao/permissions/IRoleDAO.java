package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.model.permissions.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catten on 16/6/12.
 */
public interface IRoleDAO extends JpaRepository<Role, Integer> {

    /**
     *
     * Get role by role's name
     *
     * @param name
     * @return
     */
    Role getByName(String name);

}
