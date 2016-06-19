package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

/**
 * Created by catten on 2016/6/12.
 */
public interface IArticleDAO extends PagingAndSortingRepository<Article,Integer> {
    /**
     * Find articles that match keyword in title
     *
     * @param keyword keyword
     * @return a list of articles that match keyword
     */
    Page<Article> findByTitleLike(String keyword, Pageable pageable);

    /**
     * Find articles by author
     *
     * @param author
     * @return
     */
    Page<Article> findByAuthor(User author, Pageable pageable);

    /**
     * Find articles by author id
     *
     * @param authorId
     * @return
     */
    @Query("select a from Article a where a.author.id = ?1")
    Page<Article> findByAuthorId(Integer authorId, Pageable pageable);

    /**
     * Get articles that create date between two specified date.
     *
     * @param start start date
     * @param end end date
     * @return
     */
    @Query("select a from Article a where a.date > ?1 and a.date < ?2")
    Page<Article> getArticleBetweenCreateDate(Date start, Date end, Pageable pageable);

    /**
     * Get article that modified date between two specified date.
     *
     * @param start start date
     * @param end end date
     * @return
     */
    @Query("select a from Article a where a.lastModifiedDate > ?1 and a.lastModifiedDate < ?2")
    Page<Article> getArticleBetweenModifiedDate(Date start, Date end, Pageable pageable);

}
