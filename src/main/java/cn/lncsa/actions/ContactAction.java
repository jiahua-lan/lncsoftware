package cn.lncsa.actions;

import cn.lncsa.data.model.Bulletin;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.pegdown.PegDownProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/4/27.
 */
public class ContactAction extends ActionSupport implements RequestAware {
    private static PegDownProcessor pegDownProcessor = new PegDownProcessor();

    private Map<String, Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }

    public String list(){
        requestContext.put("contactList",preRendBulletin(Bulletin.getDao().getContactInfoItems()));
        requestContext.put("friendLinks",Bulletin.getDao().getContactFriendLinks());
        return "success";
    }

    private List<Bulletin> preRendBulletin(List<Bulletin> bulletins){
        if(bulletins == null) return null;
        for(Bulletin bulletin : bulletins){
            bulletin.setContext(pegDownProcessor.markdownToHtml(bulletin.getContext()));
        }
        return bulletins;
    }
}
