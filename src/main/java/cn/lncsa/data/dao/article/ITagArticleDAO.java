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
public interface ITagArticleDAO extends JpaRepository<ArticleTag, Integer>, IRelationshipRepository<Article,Tag,ArticleTag> {

    /**
     * Remove all tags related to an article
     *
     * @param articleId
     */
    void removeAllByArticleId(Integer articleId);
}
