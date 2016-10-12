package cn.lncsa.controller;

import cn.lncsa.services.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Object queryRolePermission(Integer roleId){
        return null;
    }

    public Object queryUserPermission(Integer userId){
        return null;
    }
}
