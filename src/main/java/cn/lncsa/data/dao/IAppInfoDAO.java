package cn.lncsa.data.dao;

import cn.lncsa.data.model.AppInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by catte on 2016/6/12.
 */
public interface IAppInfoDAO extends CrudRepository<AppInfo,Integer> {
}
