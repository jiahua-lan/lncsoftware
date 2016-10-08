package cn.lncsa.data.model.user;


import cn.lncsa.data.model.abstracts.IBaseModel;
import cn.lncsa.data.model.abstracts.IRelationMaster;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "users")
public class User implements IBaseModel<Integer>, IRelationMaster {

    private Integer id;
    private String name;
    private String password;
    private String profileId;
    private Date registerDate;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
