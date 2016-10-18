package cn.lncsa.controller;

import cn.lncsa.services.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by catten on 10/13/16.
 */
@Controller
@RequestMapping("/permissions/query")
public class PermissionQueryController {

    private IPermissionServices permissionServices;

    @Autowired
    public void setPermissionServices(IPermissionServices permissionServices) {
        this.permissionServices = permissionServices;
    }

    // /permissions/query/roles/{roleId}/permissions
    public Object queryRolePermission(Integer roleId){
        return null;
    }

    // /permissions/query/user/{userId}/permissions
    public Object queryUserPermission(Integer userId){
        return null;
    }

    // /permissions/query/user/{userId}/roles

    // /permissions/query/permissions
    // PageRequest
    public Object showAllPermissions(Pageable pageable){
        return null;
    }

    // /permissions/query/roles
    // PageRequest
    public Object showAllRoles(Pageable pageable){
        return null;
    }

    // /permissions/query/role/{roleId}/users

    // /permissions/query/permission/{permissionId}/users
}
