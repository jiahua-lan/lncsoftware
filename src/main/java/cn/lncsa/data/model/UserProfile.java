package cn.lncsa.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile implements IBaseModel<Integer> {

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "profile")
    private User userId;
    private Boolean secret;
    private String nickname;
    private String gender;
    private String headPic;
    private String educationalInfo;
    private String contactingInfo;
    private Date admissionDate;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
