package cn.lncsa.data.dao.user.impl;

import cn.lncsa.data.dao.base.impl.IFieldBaseQueryDAOImpl;
import cn.lncsa.data.dao.user.IUserProfileDAO;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by catten on 10/11/16.
 */
public class IUserProfileDAOImpl extends IFieldBaseQueryDAOImpl<UserProfile> implements IUserProfileDAO {

    public IUserProfileDAOImpl(Class<UserProfile> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    //select up from UserProfile up where (select u.profileId from User u where u.id = ?1) = ?1 and up.secret = false
    public UserProfile getByUserId(final Integer userId, final boolean ignoreSecret) {
        return findOne(new Specification<UserProfile>() {
            @Override
            public Predicate toPredicate(Root<UserProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //Sub-query for "select u.profileId from User u where u.id = ?1"
                Subquery<User> userSubquery = query.subquery(User.class);
                Root<User> userRoot = userSubquery.from(User.class);
                userSubquery.select(userRoot).where(cb.equal(userRoot.get("profileId"),userId));

                //if ignoring user's secret setting, don't add secret query condition.
                return ignoreSecret ?
                        cb.exists(userSubquery) : cb.and(cb.exists(userSubquery),cb.equal(root.get("secret"),false));
            }
        });
    }

    @Override
    //select up from UserProfile up where up.id in (select u from User u where u.id in ?1) and up.secret = false
    public Page<UserProfile> getByUserId(final List<Integer> userId, final boolean ignoreSecret, Pageable pageable) {
        return findAll(new Specification<UserProfile>() {
            @Override
            public Predicate toPredicate(Root<UserProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //Sub-query for "select u.profileId from User u where u.id = ?1"
                Subquery<User> userSubquery = query.subquery(User.class);
                Root<User> userRoot = userSubquery.from(User.class);
                //Put user id in condition list
                CriteriaBuilder.In<Serializable> in = cb.in(userRoot.<Serializable>get("id"));
                for (Integer id : userId) in.value(id);
                userSubquery.select(userRoot).where(cb.equal(userRoot.get("profileId"),in));

                return ignoreSecret ?
                        cb.exists(userSubquery) : cb.and(cb.exists(userSubquery),cb.equal(root.get("secret"),false));
            }
        },pageable);
    }

}
