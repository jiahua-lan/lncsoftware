package cn.lncsa.data;

import cn.lncsa.data.model.AppInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/4/27.
 */
public class PureAppInfo {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");

    private String date;
    private String description;
    private String imageCode;
    private String link;
    private String disable;
    private String status;
    private String title;

    public PureAppInfo(AppInfo appInfo){
        date = simpleDateFormat.format(appInfo.getDate());
        description = appInfo.getDescription();
        imageCode = appInfo.getImageCode();
        link = appInfo.getLink();
        status = appInfo.getStatus();
        title = appInfo.getTitle();
        if("disable".equals(appInfo.getStatus())) disable = "disable";
    }

    public PureAppInfo(){

    }

    public static List<PureAppInfo> convertList(List<AppInfo> list){
        List<PureAppInfo> list1 = new ArrayList<>(list.size());
        for (AppInfo appInfo : list){
            list1.add(new PureAppInfo(appInfo));
        }
        return list1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
