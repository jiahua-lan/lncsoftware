package cn.lncsa.data.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Entity
@Table(name = "permissions")
public class Permission implements IBaseModel<Integer> {

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String uri;
    private Boolean enable;
    private Boolean negative;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty(message = "validate_permission_title_empty")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = "validate_permission_uri_empty")
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

    public Boolean getNegative() {
        return negative;
    }

    public void setNegative(Boolean negative) {
        this.negative = negative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
        return negative != null ? negative.equals(that.negative) : that.negative == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (negative != null ? negative.hashCode() : 0);
        return result;
    }
}
