package cn.lncsa.controller;

import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.services.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by catten on 10/13/16.
 */
@Controller
@RequestMapping("/permissions/manage")
public class PermissionManagementController {

    private IPermissionServices permissionServices;

    @Autowired
    public void setPermissionServices(IPermissionServices permissionServices) {
        this.permissionServices = permissionServices;
    }

    // /permissions/manage/{userId}/roles/save
    // role={int}...
    public Object modifyUserRole(Integer userId, List<Integer> roleList){
        return null;
    }

    // /permissions/manage/{roleId}/permissions/save
    // permission={int}...
    public Object modifyRolePermission(Integer roleId, List<Integer> permissionIds){
        return null;
    }

    // /permissions/manage/roles/save
    // Role
    public Object saveRole(Role role){
        return null;
    }

    // /permissions/manage/permissions/save
    // Permission
    public Object savePermission(Permission permission){
        return null;
    }

    // /permissions/manage/roles/{roleId}/delete
    public Object deleteRole(Integer roleId, boolean force){
        return null;
    }

    // /permissions/manage/permissions/{permissionId}/delete
    public Object deletePermission(Integer permissionId, boolean force){
        return null;
    }
}
