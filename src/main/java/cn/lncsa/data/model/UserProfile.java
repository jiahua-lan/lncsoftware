package cn.lncsa.data.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile implements IBaseModel<Integer> {

    private Integer id;

    private User user;
    private Boolean secret;
    private String nickname;
    private String gender;
    private String headPic;
    private String educationalInfo;
    private String contactingInfo;
    private Date admissionDate;

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSecret() {
        return secret;
    }

    public void setSecret(Boolean secret) {
        this.secret = secret;
    }

    @Length(max = 32)
    @Column(length = 32)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Length(max = 10)
    @Column(length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(length = 255)
    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getEducationalInfo() {
        return educationalInfo;
    }

    public void setEducationalInfo(String educationalInfo) {
        this.educationalInfo = educationalInfo;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getContactingInfo() {
        return contactingInfo;
    }

    public void setContactingInfo(String contactingInfo) {
        this.contactingInfo = contactingInfo;
    }

    @OneToOne(mappedBy = "profile")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
