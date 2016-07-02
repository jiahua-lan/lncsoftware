package cn.lncsa.services;

import cn.lncsa.common.exceptions.CommitOperateException;
import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.model.article.Commit;

/**
 * Created by catten on 16/7/2.
 */
public interface ICommitServices {
    /**
     * Make a commit to an article
     *
     * @param articleId target article id
     * @param userId    user's id
     * @param content   commit content
     * @return
     * @throws CommitOperateException if user not exist throw "User not exist", if article not exist throw "Target article not exist"
     */
    Commit commitArticle(Integer articleId, Integer userId, String content) throws CommitOperateException;

    /**
     * Make a commit reply to an existed commit.
     *
     * @param commitId target commit
     * @param userId commit user
     * @param content
     * @return
     * @throws CommitOperateException if target commit not exist throw "Commit not exist".
     * @throws UserOperateException  if commit user not exist throw "User not exist".
     */
    Commit replyToCommit(Integer commitId, Integer userId, String content) throws CommitOperateException, UserOperateException;

    /**
     * Get a commit, if commit not exist will thorw an exception
     *
     * @param commitId target id
     * @return
     * @throws CommitOperateException Target commit not exist.
     */
    Commit getCommit(Integer commitId) throws CommitOperateException;

    /**
     * Delete a commit
     *
     * @param commitId
     * @return
     * @throws CommitOperateException commit not exist.
     */
    Commit deleteCommit(Integer commitId) throws CommitOperateException;
}
