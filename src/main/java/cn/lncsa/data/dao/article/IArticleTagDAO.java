package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Handling relationship between article and tag
 *
 * Created by catten on 16/6/12.
 */
public interface IArticleTagDAO extends JpaRepository<ArticleTag,Integer> {

    /**
     * Delete tag-article relationship by article id
     *
     * @param articleId
     */
    @Modifying
    void deleteByArticleId(Integer articleId);

    /**
     * Delete tag-article relationship by tag id
     *
     * @param tagId
     */
    @Modifying
    void deleteByTagId(Integer tagId);
}
