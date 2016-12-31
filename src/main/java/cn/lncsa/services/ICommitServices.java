package cn.lncsa.services;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.User;
import cn.lncsa.services.exceptions.CommitOperateException;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.Commit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by catten on 16/7/2.
 */
public interface ICommitServices {
    /*
    *
    * Relationship modifying
    *
    * */

    /**
     * Make a commit to an article
     *
     * @param articleId target article id
     * @param userId    user's id
     * @param content   commit content
     * @return
     * @throws CommitOperateException if user not exist throw "User not exist", if article not exist throw "Target article not exist"
     */
    Commit commitArticle(Article articleId, User userId, String content) throws CommitOperateException;

    /**
     * Make a commit reply to an existed commit.
     *
     * @param commitId target commit
     * @param userId   commit user
     * @param content
     * @return
     * @throws CommitOperateException if target commit not exist throw "Commit not exist".
     * @throws UserOperateException   if commit user not exist throw "User not exist".
     */
    Commit replyToCommit(Integer commitId, User userId, String content);

    /**
     * Delete a commit
     *
     * @param commitId
     * @return
     * @throws CommitOperateException commit not exist.
     */
    void delete(Integer commitId);

    void deleteAllByArticleId(Integer articleId);

    /*
    *
    * Query methods
    *
    * */

    /**
     * Get a commit, if commit not exist will thorw an exception
     *
     * @param commitId target id
     * @return
     * @throws CommitOperateException Target commit not exist.
     */
    Commit get(Integer commitId);

    /**
     * Get commits from this user
     *
     * @param userId
     * @return
     */
    Page<Commit> get(Integer userId, Pageable pageable);
}
