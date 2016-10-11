package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

/**
 * Created by cattenlinger on 2016/10/8.
 */
public interface IUserProfileDAO extends JpaRepository<UserProfile, Integer> {

    /**
     * Get user profile by single user id
     *
     * @param userId
     * @return
     */
    @Query("select up from UserProfile up where (select u.profileId from User u where u.id = ?1) = ?1")
    UserProfile getByUserId(Integer userId);

    /**
     * Get a list of user profile
     *
     * @param userId
     * @return
     */
    @Query("select up from UserProfile up where up.id in (select u from User u where u.id in ?1)")
    Page<UserProfile> getByUserId(List<Integer> userId);

    /**
     * get user's nickname
     *
     * @param id
     * @return
     */
    @Query("select up.nickname from UserProfile up where up.id = ?1")
    String queryNickName(Integer id);

    /**
     * Query users by nickname keyword
     *
     * @param keyword
     * @param pageable
     * @return
     */
    @Query("select up from UserProfile up where up.nickname like ?1")
    Page<UserProfile> queryByNickName(String keyword, Pageable pageable);
}
