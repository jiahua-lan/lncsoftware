package cn.lncsa.data.model.permissions;

import cn.lncsa.data.model.abstracts.IBaseModel;

import javax.persistence.*;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Entity
@Table(name = "permission_roles")
public class PermissionRole implements IBaseModel {
    private Integer id;
    private Permission permission;
    private Role role;

    public PermissionRole() {
    }

    public PermissionRole(Role role, Permission permission) {
        this.permission = permission;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
