package cn.lncsa.controller;

import cn.lncsa.services.ICommitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cattenlinger on 2016/10/17.
 */
@Controller
@RequestMapping("/articles/commit")
public class CommitController {

    private ICommitServices commitServices;

    @Autowired
    public void setCommitServices(ICommitServices commitServices) {
        this.commitServices = commitServices;
    }

    // /articles/commit/{articleId}
    // Commit

    // /articles/commit/delete/{commitId}

    // /articles/commit/{articleId}/list
    // PageRequest

    // /articles/commit/get/{commitId}
    //
}
