package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.ArticleCommit;
import cn.lncsa.data.model.Commit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Handling relationship between article and commit
 *
 * Created by catte on 2016/6/12.
 */
public interface IArticleCommitDAO extends JpaRepository<ArticleCommit,Integer> {
    /**
     * Get article commit list
     *
     * @param article
     * @param pageable
     * @return
     */
    @Query("select ac.commit from ArticleCommit ac where ac.article = ?1")
    Page<Commit> getCommitsByArticle(Article article, Pageable pageable);

    /**
     * Get article commit list by article id
     *
     * @param articleId
     * @param pageable
     * @return
     */
    @Query("select ac.commit from ArticleCommit ac where ac.article.id = ?1")
    Page<Commit> getCommitsByArticleId(Integer articleId, Pageable pageable);
}
