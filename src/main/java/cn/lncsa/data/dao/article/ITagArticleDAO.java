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
 * <p>
 * Created by catten on 16/6/12.
 */
public interface ITagArticleDAO extends JpaRepository<ArticleTag, Integer>{

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

    /**
     * Get article's tag
     *
     * @param article
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article = ?1")
    List<Tag> getByArticle(Article article);

    /**
     * Get article's tag by article id
     *
     * @param articleId
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article.id = ?1")
    List<Tag> getByArticleId(Integer articleId);

    /**
     * Get ArticleTag relationship object
     *
     * @param articleId
     * @param tags
     * @return
     */
    @Query("select at from ArticleTag at where at.article.id = ?1 and at.tag in ?2")
    List<ArticleTag> getRelationships(Integer articleId, List<Tag> tags);

    /**
     * Find article by tags
     *
     * @param tags     a list of tags
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at join at.article where at.tag in ?1 and at.article.status in ?2")
    Page<Article> findArticleByTags(List<Tag> tags, String[] status, Pageable pageable);
}
