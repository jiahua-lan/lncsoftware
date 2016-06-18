package cn.lncsa.data;

import cn.lncsa.data.model.Right;
import cn.lncsa.data.model.User;

import java.util.List;

/**
 * Created by catten on 16/6/13.
 */
public class Passport {
    private User user;
    private List<Right> rights;

    public Passport(User user, List<Right> rights) {
        this.user = user;
        this.rights = rights;
    }

    public Passport(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
}
