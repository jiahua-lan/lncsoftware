package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.dao.base.domain.IRelationshipRepository;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.permissions.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Handling relationship between user and role.
 *
 * Created by catte on 2016/6/12.
 */
public interface IRoleUserDAO extends PagingAndSortingRepository<UserRole,Integer>, IRelationshipRepository<User,Role,UserRole> {

}
