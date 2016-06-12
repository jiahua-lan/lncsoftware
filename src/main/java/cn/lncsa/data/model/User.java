package cn.lncsa.data.model;


import javax.persistence.*;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "user")
public class User{

    private Integer id;
    private String name;
    private String password;
    private String contactInfo;

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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
