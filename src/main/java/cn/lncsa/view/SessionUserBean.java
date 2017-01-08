package cn.lncsa.view;

import cn.lncsa.data.model.User;
import cn.lncsa.data.model.UserProfile;

/**
 * Created by cattenlinger on 2017/1/9.
 */
public class SessionUserBean {
    private Integer userId;
    private String username;
    private String nickname;
    private String headPic;

    public SessionUserBean(){

    }

    public SessionUserBean(User user){
        this.userId = user.getId();
        this.username = user.getName();
        UserProfile userProfile = user.getProfile();
        if(userProfile != null){
            this.nickname = userProfile.getNickname();
            this.headPic = userProfile.getHeadPic();
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
