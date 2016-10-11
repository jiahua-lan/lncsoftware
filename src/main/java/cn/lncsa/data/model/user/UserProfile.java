package cn.lncsa.data.model.user;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    private Integer id;
    private Boolean secret;
    private String nickname;
    private String realName;
    private String gender;
    private String headPic;
    private String attendClass;
    private String phoneNumber;
    private String qq_account;
    private String weChat_account;
    private String email;
    private Date birthday;
    private Date admissionDate;

    @Id
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getAttendClass() {
        return attendClass;
    }

    public void setAttendClass(String attendClass) {
        this.attendClass = attendClass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq_account() {
        return qq_account;
    }

    public void setQq_account(String qq_account) {
        this.qq_account = qq_account;
    }

    public String getWeChat_account() {
        return weChat_account;
    }

    public void setWeChat_account(String weChat_account) {
        this.weChat_account = weChat_account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}
