package cn.lncsa.services.impl;

import cn.lncsa.services.exceptions.CommitOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Commit;
import cn.lncsa.data.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Commit commitArticle(Integer articleId, Integer userId, String content){
        Commit commit = new Commit(userId, content, articleId);
        commit.setDate(new Date());
        return commitDAO.save(commit);
    }

    @Override
    public Commit replyToCommit(Integer commitId, Integer userId, String content){
        Commit commit = get(commitId);
        Commit replyCommit = new Commit(userId, content, commit.getTargetArticle(), commit);
        replyCommit.setDate(new Date());
        return commitDAO.save(replyCommit);
    }

    @Override
    public Commit get(Integer commitId){
        return commitDAO.findOne(commitId);
    }

    @Override
    public Page<Commit> getUserCommits(Integer userId, Pageable pageable) {
        return commitDAO.getByUserId(userId, pageable);
    }

    @Override
    public void delete(Integer commitId) {
        commitDAO.delete(commitId);
    }


}
