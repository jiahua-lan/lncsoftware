package cn.lncsa.data.model.permissions;

import javax.persistence.*;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Entity
@Table(name = "permissions")
public class Permission {
    private Integer id;
    private String title;
    private String uri;
    private Boolean enable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
