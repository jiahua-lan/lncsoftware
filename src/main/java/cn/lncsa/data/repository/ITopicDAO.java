package cn.lncsa.data.repository;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by catte on 2016/6/12.
 */
public interface ITopicDAO extends IBaseDAO<Topic> {

}
