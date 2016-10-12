package cn.lncsa.data.dao.user.impl;

import cn.lncsa.data.dao.base.impl.IFieldBaseQueryDAOImpl;
import cn.lncsa.data.dao.user.IUserProfileDAO;
import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by catten on 10/11/16.
 */
public class IUserProfileDAOImpl extends IFieldBaseQueryDAOImpl<UserProfile,Integer> implements IUserProfileDAO {

    public IUserProfileDAOImpl(Class<UserProfile> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public UserProfile getByUserId(Integer userId) {
        Query query = getEntityManager().createQuery("select up from UserProfile up where (select u.profileId from User u where u.id = ?1) = ?1 and up.secret = false");
        query.setParameter(1,userId);
        return (UserProfile) query.getSingleResult();
    }

    @Override
    public Page<UserProfile> getByUserId(List<Integer> userId, Pageable pageable) {
        Query query = getEntityManager().createQuery("select up from UserProfile up where (select u.profileId from User u where u.id = ?1) = ?1 and up.secret = false");
        query.setParameter(1,userId);
        return null;
    }

    @Override
    public Page<UserProfile> getByUserIdIgnoreSecret(Integer userId) {
        return null;
    }

}
