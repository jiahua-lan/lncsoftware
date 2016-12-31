package cn.lncsa.data.repository;

import cn.lncsa.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by catte on 2016/6/12.
 */
public interface IUserDAO extends IBaseDAO<User> {

    /**
     * Get user by name
     *
     * @param name username
     * @return a user
     */
    @Query("select u from User u where u.name = ?1")
    User getByName(String name);

    /**
     * Search users that name include keyword
     *
     * @param keyword
     * @return
     */
    @Query("select u from User u where u.name like ?1")
    Page<User> getByNameLike(String keyword, Pageable pageable);

    /**
     * Using profile's id to query user
     *
     * @param profileId
     * @return
     */
    @Query("select u from User u where u.profile = ?1")
    User getByProfileId(Integer profileId);

    /**
     * Get user by register date range
     *
     * @param startDate
     * @param endDate
     * @param pageable
     * @return
     */
    @Query("select u from User u where u.registerDate >= ?1 and u.registerDate <= ?2")
    Page<User> getByRegisterDateRange(Date startDate, Date endDate, Pageable pageable);

}
