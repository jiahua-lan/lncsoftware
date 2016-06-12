package cn.lncsa.data.dao;

import cn.lncsa.data.model.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catten on 16/6/12.
 */
public interface IArticleTagDAO extends JpaRepository<ArticleTag,Integer> {
}
