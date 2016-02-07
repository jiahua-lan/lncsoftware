package cn.lncsoftware.data.factory;

import cn.lncsoftware.data.common.Connector;
import cn.lncsoftware.data.AppInfo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class AppInfoDAO extends DOFactory<AppInfo>{

    public AppInfoDAO() {
        super("appInfo");
    }

    @Override
    public AppInfo getDataInstance(Document doDoc) {
        return new AppInfo(doDoc);
    }
}
