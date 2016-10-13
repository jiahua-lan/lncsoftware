package cn.lncsa.data.model.permissions;

import cn.lncsa.data.model.base.IBaseModel;
import cn.lncsa.data.model.domain.IRelationship;
import cn.lncsa.data.model.user.User;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "user_roles")
public class UserRole implements IRelationship<User,Role,Integer> {

    private Integer id;
    private User user;
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    @Transient
    public User getMaster() {
        return user;
    }

    @Override
    @Transient
    public Role getSlave() {
        return role;
    }

    @Override
    @Transient
    public void setRelationship(User master, Role slave) {
        user = master;
        role = slave;
    }
}
