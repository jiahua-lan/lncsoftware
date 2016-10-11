package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@NoRepositoryBean
public interface IUserProfileDAO extends JpaRepository<UserProfile, Integer> {

    /**
     * Get user profile by single user id
     *
     * @param userId
     * @return
     */
    @Query("select up from UserProfile up where (select u.profileId from User u where u.id = ?1) = ?1 and up.secret = false ")
    UserProfile getByUserId(Integer userId);

    /**
     * Get a list of user profile
     *
     * @param userId
     * @return
     */
    @Query("select up from UserProfile up where up.id in (select u from User u where u.id in ?1) and up.secret = false ")
    Page<UserProfile> getByUserId(List<Integer> userId, Pageable pageable);

    /**
     * Get a list of user profile ignore user's secret setting
     *
     * @param userId
     * @return
     */
    @Query("select up from UserProfile up where up.id in (select u from User u where u.id in ?1)")
    Page<UserProfile> getByUserIdIgnoreSecret(Integer userId);
}
