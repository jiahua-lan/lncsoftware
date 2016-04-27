package cn.lncsa.actions;

import cn.lncsa.data.PureAppInfo;
import cn.lncsa.data.model.AppInfo;
import cn.lncsa.data.model.Bulletin;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/4/27.
 */
public class AppAction extends ActionSupport implements RequestAware {

    private Map<String, Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
        requestContext.put("bulletin", Bulletin.getDao().getAppGuideBulletin());
        requestContext.put("bulletinBoard", Bulletin.getDao().getAppInfoBulletin());
    }

    public String list(){
        List<AppInfo> appInfos = AppInfo.getDao().listAll();
        if(appInfos != null) requestContext.put("appList", PureAppInfo.convertList(appInfos));
        else requestContext.put("appList",null);
        return "success";
    }
}
