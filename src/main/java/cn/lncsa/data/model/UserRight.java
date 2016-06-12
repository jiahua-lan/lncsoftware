package cn.lncsa.data.model;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "user_rights")
public class UserRight {

    private Integer id;
    private User user;
    private Right right;

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
    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }
}
