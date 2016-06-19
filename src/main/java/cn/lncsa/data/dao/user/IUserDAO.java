package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface IUserDAO extends JpaRepository<User,Integer> {

    /**
     * Get user by name
     *
     * @param name username
     * @return a user
     */
    User getByName(String name);

    /**
     * Search users that name include keyword
     *
     * @param keyword
     * @return
     */
    Page<User> getByNameLike(String keyword);

    /**
     * Search users that nickname include keyword
     *
     * @param keyword
     * @return
     */
    Page<User> getByNickNameLike(String keyword);

    /**
     * Search users that contact info include keyword
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<User> getByContactInfoLike(String keyword, Pageable pageable);
}
