package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.ArticleTag;
import cn.lncsa.data.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Handling relationship between article and tag
 *
 * Created by catten on 16/6/12.
 */
public interface IArticleTagDAO extends JpaRepository<ArticleTag,Integer> {
    /**
     * Find articles by tag
     *
     * @param tag an existed tag
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at where at.tag = ?1")
    Page<Article> findArticleByTag(Tag tag, Pageable pageable);

    /**
     * Find articles by tag's id
     *
     * @param tagId an existed tag's id
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at where at.tag.id = ?1")
    Page<Article> findArticleByTagId(Integer tagId, Pageable pageable);

    /**
     * Get article's tag
     *
     * @param article
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article = ?1")
    List<Tag> getTagsByArticle(Article article);

    /**
     * Get article's tag by article id
     *
     * @param articleId
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article.id = ?1")
    List<Tag> getTagsByArticleId(Integer articleId);
}
