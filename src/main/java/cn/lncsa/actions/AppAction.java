package cn.lncsa.actions;

import cn.lncsa.data.dao.IAppInfoDAO;
import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.AppInfo;
import cn.lncsa.data.model.Bulletin;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/4/27.
 */
public class AppAction extends ActionSupport implements RequestAware {


    private IBulletinDAO bulletinDAO;
    private IAppInfoDAO appInfoDAO;

    private Map<String, Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
        Bulletin appGuide = bulletinDAO.getAppGuideBulletin();
        Bulletin appInfo = bulletinDAO.getAppUsageBulletin();
        //requestContext.put("bulletin",appGuide);
        //requestContext.put("bulletinBoard",appInfo);
    }

    public String list(){
        List<AppInfo> appInfos = Collections.list((Enumeration<AppInfo>) appInfoDAO.findAll());
        requestContext.put("appList", appInfos);
        return "success";
    }

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }
}
