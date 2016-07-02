package cn.lncsa.services.impl;

import cn.lncsa.common.exceptions.CommitOperateException;
import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Commit;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by catten on 16/7/2.
 */
@Service
public class CommitServices implements cn.lncsa.services.ICommitServices {
    private ICommitDAO commitDAO;
    private IArticleDAO articleDAO;
    private IUserDAO userDAO;

    private IUserServices userServices;

    @Autowired
    public void setCommitDAO(ICommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public Commit commitArticle(Integer articleId, Integer userId, String content) throws CommitOperateException {
        Article article = articleDAO.findOne(articleId);
        if (article == null) throw new CommitOperateException("Target article not found");
        User user = userDAO.findOne(userId);
        if (user == null) throw new CommitOperateException("User not exist");
        Commit commit = new Commit(user, content, article);
        commit.setDate(new Date());
        return commitDAO.save(commit);
    }

    @Override
    public Commit replyToCommit(Integer commitId, Integer userId, String content) throws CommitOperateException, UserOperateException {
        User user = userServices.getUser(userId);
        Commit commit = getCommit(commitId);
        Commit replyCommit = new Commit(user, content, commit);
        replyCommit.setDate(new Date());
        return commitDAO.save(replyCommit);
    }

    @Override
    public Commit getCommit(Integer commitId) throws CommitOperateException {
        Commit commit = commitDAO.findOne(commitId);
        if (commit == null) throw new CommitOperateException("Target commit not exist.");
        return commit;
    }

    @Override
    public Commit deleteCommit(Integer commitId) throws CommitOperateException {
        Commit commit = getCommit(commitId);
        commitDAO.delete(commitId);
        return commit;
    }


}
