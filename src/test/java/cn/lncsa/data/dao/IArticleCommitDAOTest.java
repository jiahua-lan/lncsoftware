package cn.lncsa.data.dao;

import cn.lncsa.data.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by catten on 16/6/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        //如果测试文件有依赖，则按照这个顺序加载，如果直接使用ContextConfiguration则只加载一个
        @ContextConfiguration("classpath:/cn/lncsa/applicationContext.xml")
})
//测试里事务默认回滚，但是建议加上
@Transactional
public class IArticleCommitDAOTest {
    private IArticleDAO articleDAO;
    private ICommitDAO commitDAO;
    private IArticleCommitDAO articleCommitDAO;

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setCommitDAO(ICommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Autowired
    public void setArticleCommitDAO(IArticleCommitDAO articleCommitDAO) {
        this.articleCommitDAO = articleCommitDAO;
    }

    @Before
    public void setUp(){
        List<ArticleCommit> articleCommitList;
        //For making a random size commit list
        Random random = new Random();

        //Make 10 articles
        for (int i = 0; i < 10; i++){
            Article article = new Article();
            //Make a random size commit list for the article
            int commitListSize = random.nextInt(30);
            articleCommitList = new LinkedList<>();

            //Make a test article
            article.setTitle("Test article " + i);
            article.setStatus("shown");
            article.setContext(String.format("#Test article%d with commits\n\nTotal %d commits.", i, commitListSize));
            article.setDate(new Date());
            articleDAO.save(article);

            //Set up a commit list
            for(int c = 0 ; c < commitListSize; c++){
                Commit commit = new Commit();
                commit.setDate(new Date());
                commit.setContents(String.format("Test commit %d for %s", c, article.getTitle()));
                commitDAO.save(commit);
                articleCommitList.add(new ArticleCommit(article,commit));
            }

            //Save the commit list
            articleCommitDAO.save(articleCommitList);
        }
    }

    @Test
    public void getCommitsByArticle() throws Exception {
        Iterable<Article> articles = articleDAO.findAll();
        for (Article article : articles){
            Page<Commit> commitPage = articleCommitDAO.getCommitsByArticle(article,new PageRequest(0,5));
            System.out.printf("%s : total %d commits. %d pages.%n",
                    article.getTitle(),
                    commitPage.getTotalElements(),
                    commitPage.getTotalPages()
            );
        }
    }

    @Test
    public void getCommitsByArticleId() throws Exception {
        Iterable<Article> articles = articleDAO.findAll();
        for (Article article : articles){
            Page<Commit> commitPage = articleCommitDAO.getCommitsByArticleId(article.getId(),new PageRequest(0,5));
            System.out.printf("%s : total %d commits. %d pages.%n",
                    article.getTitle(),
                    commitPage.getTotalElements(),
                    commitPage.getTotalPages()
            );
        }
    }

}