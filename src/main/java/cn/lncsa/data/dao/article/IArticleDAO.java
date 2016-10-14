package cn.lncsa.data.dao.article;

import cn.lncsa.data.dao.base.IFieldBaseQueryDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by catten on 2016/6/12.
 */
public interface IArticleDAO extends PagingAndSortingRepository<Article, Integer>, IFieldBaseQueryDAO<Article> {

    enum DateType{
        createDate,
        modifiedDate
    }

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
    Page<Article> getArticleBetweenDate(Date start, Date end,DateType dateType, String[] status, Pageable pageable);

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
     * Get article by tags
     *
     * Due to Spring Data JPA designing, a specific entity should managed by their DAO, so
     * this method can't put in to ITagArticleDAO.
     *
     * @param tags
     * @param status
     * @param pageable
     * @return
     */
    Page<Article> getByTags(List<Tag> tags,List<String> status, Pageable pageable);

    /**
     * Delete articles by author id
     *
     * @param authorId
     */
    void deleteByAuthorId(Integer authorId);

}
