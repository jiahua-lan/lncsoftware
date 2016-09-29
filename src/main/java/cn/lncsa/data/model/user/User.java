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
    private String nickName;
    private String password;
    private Date registerDate;
    private String contactInfo;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String contactInfo) {
        this.name = name;
        this.password = password;
        this.contactInfo = contactInfo;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
