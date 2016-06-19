package cn.lncsa.data.dao.article;

import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.IArticleTagDAO;
import cn.lncsa.data.dao.article.ITagDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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
//TODO 这个测试用例太水，需要重写
public class IArticleTagDAOTest {
    private IArticleDAO articleDAO;
    private ITagDAO tagDAO;
    private IArticleTagDAO articleTagDAO;

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setTagDAO(ITagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Autowired
    public void setArticleTagDAO(IArticleTagDAO articleTagDAO) {
        this.articleTagDAO = articleTagDAO;
    }

    @Before
    public void setUp(){
        Random random = new Random();
        //Make some tags
        for (int i = 0; i < 5; i++) tagDAO.save(new Tag("Tag" + i));
        List<Tag> tagList = tagDAO.findAll();
        //Set some article randomly for each tag
        for(Tag tag : tagList){
            for (int i = 0; i < random.nextInt(20); i++) {
                Article article = new Article();
                articleDAO.save(article);
                articleTagDAO.save(new ArticleTag(tag, article));
            }
        }
    }

    @Test
    public void findArticleByTag() throws Exception {
        List<Tag> tagList = tagDAO.findAll();
        for (Tag tag : tagList){
            Page<Article> articles = articleTagDAO.findArticleByTag(tag,new PageRequest(0,5));
            System.out.printf("Total %d articles. %d pages.%n", articles.getTotalElements(), articles.getTotalPages());
        }
    }

    @Test
    public void findArticleByTagId() throws Exception {
        List<Tag> tagList = tagDAO.findAll();
        for (Tag tag : tagList){
            Page<Article> articles = articleTagDAO.findArticleByTagId(tag.getId(),new PageRequest(0,5));
            System.out.printf("Total %d articles. %d pages.%n", articles.getTotalElements(), articles.getTotalPages());
        }
    }

    @Test
    public void getTagsByArticle() throws Exception {
        Iterable<Article> articles = articleDAO.findAll();
        for (Article article : articles){
            List<Tag> tagList = articleTagDAO.getTagsByArticle(article);
            System.out.printf("Total %d tags", tagList.size());
        }
    }

    @Test
    public void getTagsByArticleId() throws Exception {
        Iterable<Article> articles = articleDAO.findAll();
        for (Article article : articles){
            List<Tag> tagList = articleTagDAO.getTagsByArticleId(article.getId());
            System.out.printf("Total %d tags", tagList.size());
        }
    }

}