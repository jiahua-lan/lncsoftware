package cn.lncsa.data.model.permissions;

import cn.lncsa.data.domain.IBaseModel;
import cn.lncsa.data.domain.IRelationMaster;
import cn.lncsa.data.domain.IRelationSlave;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "roles")
public class Role implements IBaseModel<Integer>, IRelationMaster, IRelationSlave {
    private Integer id;
    private String name;
    private Boolean enable;
    private String color;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return name.equals(role.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
