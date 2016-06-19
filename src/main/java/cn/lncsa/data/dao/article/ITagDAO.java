package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catte on 2016/6/12.
 */
public interface ITagDAO extends JpaRepository<Tag,Integer> {

}
