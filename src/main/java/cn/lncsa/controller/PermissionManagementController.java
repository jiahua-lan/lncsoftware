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

    public Object modifyUserRole(Integer userId, List<Integer> roleList){
        return null;
    }

    public Object modifyRolePermission(Integer roleId, List<Permission> permissionList){
        return null;
    }

    public Object saveRole(Role role){
        return null;
    }

    public Object savePermission(Permission permission){
        return null;
    }

    public Object deleteRole(Integer roleId){
        return null;
    }

    public Object deletePermission(Integer permissionId){
        return null;
    }
}
