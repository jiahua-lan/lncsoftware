package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by catten on 2016/6/12.
 */
public interface IArticleDAO extends PagingAndSortingRepository<Article, Integer> {
    /**
     * Find articles that match keyword in title
     *
     * @param keyword  keyword
     * @param status   what status allow
     * @param pageable
     * @return a list of articles that match keyword
     */
    @Query("select a from Article a where a.title like ?1 and a.status in ?2")
    Page<Article> findByTitleLike(String keyword, String[] status, Pageable pageable);

    /**
     * Find articles by author id
     *
     * @param authorId
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.authorId = ?1 and a.status in ?2")
    Page<Article> findByAuthorId(Integer authorId, String[] status, Pageable pageable);

    /**
     * Get articles that create date between two specified date.
     *
     * @param start    start date
     * @param end      end date
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.createDate > ?1 and a.createDate < ?2 and a.status in ?2")
    Page<Article> getArticleBetweenCreateDate(Date start, Date end, String[] status, Pageable pageable);

    /**
     * Get article that modified date between two specified date.
     *
     * @param start    start date
     * @param end      end date
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.lastModifiedDate > ?1 and a.lastModifiedDate < ?2")
    Page<Article> getArticleBetweenModifiedDate(Date start, Date end, String[] status, Pageable pageable);

    /**
     * Find article by tags
     *
     * @param tags        a list of tags
     * @param status      what status allow
     * @param pageable
     * @return
     */
    @Query("select at.article from ArticleTag at join at.article where at.tag in ?1 and at.article.status in ?2")
    Page<Article> findArticleByTags(List<Tag> tags, String[] status, Pageable pageable);

    /**
     * Find all article
     *
     * @param status      what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.status in ?1")
    Page<Article> findAll(String[] status, Pageable pageable);

    /**
     * Delete articles by author id
     *
     * @param authorId
     */
    @Modifying
    @Query("delete from Article a where a.authorId = ?1")
    void deleteArticleByAuthorId(Integer authorId);

}
