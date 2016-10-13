package cn.lncsa.data.dao.article;

import cn.lncsa.data.dao.base.domain.IRelationshipRepository;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Handling relationship between master and tag
 * <p>
 * Created by catten on 16/6/12.
 */
@NoRepositoryBean
public interface ITagArticleDAO extends JpaRepository<ArticleTag, Integer>, IRelationshipRepository<Article,Tag,ArticleTag> {

    /**
     * Get master's tag
     *
     * @param article
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article = ?1")
    List<Tag> getByArticle(Article article);

    /**
     * Get master's tag by master id
     *
     * @param articleId
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article.id = ?1")
    List<Tag> getByArticleId(Integer articleId);

    /**
     * Get relationships by tag
     *
     * @param tag
     * @return
     */
    @Override
    @Query("select at from ArticleTag at where at.tag = ?1")
    List<ArticleTag> getRelationships(Tag tag);

    /**
     * Get relationships by master
     *
     * @param article
     * @return
     */
    @Query("select at from ArticleTag at where at.article = ?1")
    List<ArticleTag> getRelationships(Article article);

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
     * Get ArticleTag relationship object
     *
     * @param master
     * @param slave
     * @return
     */
    @Query("select at from ArticleTag at where at.article = ?1 and at.tag in ?2")
    List<ArticleTag> getRelationships(Article master, List<Tag> slave);

    /**
     * Find master by slave
     *
     * @param tag     tag
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at join at.article where at.tag = ?1 and at.article.status in ?2")
    Page<Article> findArticleByTag(Tag tag, List<String> status, Pageable pageable);

    @Modifying
    @Query("delete from ArticleTag at where at.article = ?1")
    void removeAllByArticleId(Article article);
}
