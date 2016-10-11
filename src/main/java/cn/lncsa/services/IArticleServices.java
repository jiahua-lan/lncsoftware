package cn.lncsa.services;

import cn.lncsa.data.model.article.ArticleBody;
import cn.lncsa.services.exceptions.ArticleOperateException;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by catten on 16/7/1.
 */
public interface IArticleServices {

    /**
     * Save an article
     *
     * @param article article which want to save
     * @param body    the body of this article
     * @return
     * @throws ArticleOperateException if save to article that have id but it not exist, throw "Article not exist"
     */
    void save(Article article, ArticleBody body);

    /**
     * Only save article head
     *
     * @param article
     */
    void saveHead(Article article);

    /**
     * Only save article body
     *
     * @param articleBody
     */
    void saveBody(ArticleBody articleBody);

    /**
     * Delete an article by Id
     * <p>
     * Do not use this method frequency, it will make heavy database transaction.
     * If wanna to delete an article, as much as possible to set it "banned" rather than delete it
     *
     * @param articleId an exist article's id
     * @return article data which was deleted
     * @throws ArticleOperateException article not exist
     */
    void delete(Integer articleId);

    /*
    *
    * Query Methods
    *
    * */

    /**
     * Get one article by id;
     *
     * @param articleId
     * @return
     */
    Article get(Integer articleId);

    /**
     * Get all article
     *
     * @param pageable paging request
     * @return
     */
    Page<Article> get(Pageable pageable, String... status);

    List<Article> getLatest(Integer count, String... status);

    /**
     * Get article by a list tag
     *
     * @param tag
     * @param pageable
     * @param status   what status allow
     * @return
     */
    Page<Article> getByTag(Tag tag, Pageable pageable, String... status);

    /**
     * Get article by userId
     *
     * @param userId
     * @param pageable
     * @param status   what status allow
     * @return
     */
    Page<Article> getByUserId(Integer userId, Pageable pageable, String... status);

    /**
     * Find article between date
     *
     * @param startDate
     * @param endDate
     * @param pageable
     * @param status    what status allow
     * @return
     */
    Page<Article> findBetweenDate(Date startDate, Date endDate, Pageable pageable, String... status);

    /**
     * Find articles between date
     *
     * @param startDate
     * @param endDate
     * @param pageable
     * @param status    what status allow
     * @return
     */
    Page<Article> findBetweenModifiedDate(Date startDate, Date endDate, Pageable pageable, String... status);

    /**
     * Full article context searching
     *
     * @param keyword  some keywords
     * @param pageable
     * @param status
     * @return
     */
    Page<Article> findByKeyword(String keyword, Pageable pageable, String... status);

    /**
     * Get article content
     *
     * @param articleId
     * @return
     */
    ArticleBody getBody(Integer articleId);
}
