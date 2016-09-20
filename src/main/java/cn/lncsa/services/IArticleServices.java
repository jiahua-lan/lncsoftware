package cn.lncsa.services;

import cn.lncsa.common.exceptions.ArticleOperateException;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by catten on 16/7/1.
 */
public interface IArticleServices {
    /**
     * Get latest article list.
     *
     * @return a list of latest article
     */
    List<Article> getLatestArticle();

    /**
     * Save an article and tag it
     *
     * @param article article which want to save
     * @param tags    tags point to the article, will not make any change if set null
     * @return
     * @throws ArticleOperateException if save to article that have id but it not exist, throw "Article not exist"
     */
    Article saveArticle(Article article, List<Tag> tags) throws ArticleOperateException;

    /**
     * Delete an article by Id
     * <p>
     * Do not use this method frequency, it will make heavy database transaction.
     *
     * @param articleId an exist article's id
     * @return article data which was deleted
     * @throws ArticleOperateException article not exist
     */
    @Transactional
    Article deleteArticle(Integer articleId) throws ArticleOperateException;

    /**
     * Set article status
     * <p>
     * If wanna to delete an article, as much as possible to set it "banned" rather than delete it
     *
     * @param articleId
     * @param status
     * @return
     * @throws ArticleOperateException Article not exist.
     */
    Article setArticleStatus(Integer articleId, String status) throws ArticleOperateException;

    /**
     * Get all article
     *
     * @param pageable paging request
     * @return
     */
    Page<Article> getAllArticle(Pageable pageable, String... status);

    /**
     * Get article by a list tags
     *
     * @param tags
     * @param pageable
     * @param status      what status allow
     * @return
     */
    Page<Article> getArticleByTags(List<Tag> tags, Pageable pageable, String... status);

    /**
     * Get article by userId
     *
     * @param userId
     * @param pageable
     * @param status what status allow
     * @return
     */
    Page<Article> getArticleByUserId(Integer userId, Pageable pageable, String... status);

    /**
     * Find article between date
     *
     * @param startDate
     * @param endDate
     * @param pageable
     * @param status what status allow
     * @return
     */
    Page<Article> findArticleBetweenDate(Date startDate, Date endDate, Pageable pageable, String... status);

    /**
     * Find articles between date
     *
     * @param startDate
     * @param endDate
     * @param pageable
     * @param status what status allow
     * @return
     */
    Page<Article> findArticleBetweenModifiedDate(Date startDate, Date endDate, Pageable pageable, String... status);

    /**
     * Get all tags tagged to an article
     *
     * @param articleId
     * @return
     */
    List<Tag> getArticleTags(Integer articleId);

    /**
     * Full article context searching
     *
     * @param keyword some keywords
     * @param pageable
     * @param status
     * @return
     */
    Page<Article> findArticleByKeyword(String keyword, Pageable pageable, String... status);

    /**
     * Get a tag object by tag's name
     *
     * @param tag tag's name
     * @return tag object
     */
    Tag getTagByTitle(String tag);
}
