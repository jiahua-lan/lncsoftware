package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Commit;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by catten on 2016/6/12.
 */
public interface ICommitDAO extends PagingAndSortingRepository<Commit,Integer> {

    /**
     * Get commit by user
     *
     * @param user
     * @return
     */
    Page<Commit> getByUser(User user, Pageable pageable);

    /**
     * Get commit by user id
     *
     * @param userId
     * @return
     */
    @Query("select c from Commit c where c.user.id = ?1")
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
    @Query("select c from Commit c where c.targetArticle.id = ?1")
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
}
