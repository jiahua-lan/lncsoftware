package cn.lncsa.data.model.permissions;

import cn.lncsa.data.model.base.IBaseModel;
import cn.lncsa.data.model.domain.IRelationSlave;

import javax.persistence.*;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Entity
@Table(name = "permissions")
public class Permission implements IRelationSlave<Integer> {
    private Integer id;
    private String title;
    private String uri;
    private Boolean enable;
    private Boolean negative;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        return title.equals(that.title) && uri.equals(that.uri);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }

    public Boolean getNegative() {
        return negative;
    }

    public void setNegative(Boolean negative) {
        this.negative = negative;
    }
}
