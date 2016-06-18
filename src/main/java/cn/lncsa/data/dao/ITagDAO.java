package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface ITagDAO extends JpaRepository<Tag,Integer> {

}
