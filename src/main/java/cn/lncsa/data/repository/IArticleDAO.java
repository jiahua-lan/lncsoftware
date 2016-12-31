package cn.lncsa.data.repository;

import cn.lncsa.data.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by catten on 2016/6/12.
 */
public interface IArticleDAO extends IBaseDAO<Article>{

    enum DateType{
        createDate,
        modifiedDate
    }

    /**
     * Get articles that create date between two specified date.
     *
     * @param start    start date
     * @param end      end date
     * @param status   what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.createDate between ?1 and ?2 and a.status in ?3")
    Page<Article> getArticleBetweenDate(Date start, Date end, String[] status, Pageable pageable);

    /**
     * Find all article
     *
     * @param status      what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.status in ?1")
    Page<Article> findAll(String[] status, Pageable pageable);

}
