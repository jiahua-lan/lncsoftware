package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.ArticleBody;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catten on 10/11/16.
 */
public interface IArticleBodyDAO extends JpaRepository<ArticleBody,Integer> {
}
