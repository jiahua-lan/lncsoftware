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
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Handling relationship between master and tag
 * <p>
 * Created by catten on 16/6/12.
 */
public interface ITagArticleDAO extends CrudRepository<ArticleTag, Integer>, IRelationshipRepository<Article,Tag,ArticleTag> {

    /**
     * Remove all tags related to an article
     *
     * @param article
     */
    @Modifying
    @Query("delete from ArticleTag at where at.article = ?1")
    void removeAllByArticleId(Article article);

    /**
     * Get article by tags
     *
     *
     * @param tags
     * @param status
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at join Article where at.tag in ?1 and at.article.status in ?2")
    Page<Article> getByTags(List<Tag> tags,List<String> status, Pageable pageable);

    /**
     * Get tags tagged to an article
     *
     * @{see} cn.lncsa.data.dao.article.IArticleDAO getByTags()
     *
     * @param article
     * @return
     */
    @Query("select at.tag from ArticleTag at where at.article = ?1")
    List<Tag> getByArticle(Article article);
}
