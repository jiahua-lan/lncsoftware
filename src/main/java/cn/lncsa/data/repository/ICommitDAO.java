package cn.lncsa.data.repository;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Commit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by catten on 2016/6/12.
 */
public interface ICommitDAO extends IBaseDAO<Commit> {

    /**
     * Get commit by user id
     *
     * @param userId
     * @return
     */
    @Query("select c from Commit c where c.user = ?1")
    Page<Commit> getByUserId(Integer userId, Pageable pageable);

    /**
     * Get article's commits
     *
     * @param targetArticle
     * @return
     */
    Page<Commit> getByTargetArticle(Article targetArticle, Pageable pageable);

    /**
     * Get article's commits by article's id
     *
     * @param targetArticleId
     * @return
     */
    @Query("select c from Commit c where c.targetArticle = ?1")
    Page<Commit> getByTargetArticleId(Integer targetArticleId, Pageable pageable);

    /**
     * Get what this commit reply to
     *
     * @param commit
     * @return
     */
    Commit getByReplyTo(Commit commit);

    /**
     * Get what this commit reply to by id
     *
     * @param commitId
     * @return
     */
    @Query("select c from Commit c where c.replyTo.id = ?1")
    Commit getByReplyToById(Integer commitId);

    /**
     * Delete commits by user's id
     *
     * @param userId
     */
    @Modifying
    @Query("delete from Commit c where c.user = ?1")
    void deleteByUserId(Integer userId);

    /**
     * Delete all commits wired to an article
     *
     * @param articleId
     */
    @Modifying
    void deleteByTargetArticle(Integer articleId);
}
