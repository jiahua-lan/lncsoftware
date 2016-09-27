package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.permissions.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionDAO extends JpaRepository<Permission,Integer> {

}
