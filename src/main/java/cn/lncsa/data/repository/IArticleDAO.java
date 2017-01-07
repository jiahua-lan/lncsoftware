package cn.lncsa.data.repository;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by catten on 2016/6/12.
 */
public interface IArticleDAO extends IBaseDAO<Article>{
    /**
     * Find all article
     *
     * @param status      what status allow
     * @param pageable
     * @return
     */
    @Query("select a from Article a where a.status in ?1")
    Page<Article> findAll(String[] status, Pageable pageable);

    @Query("select a from Article a join fetch a.topics t where a.status in ?2 and a.topics = ?1")
    Page<Article> findByTopic(Topic topic, String[] status, Pageable pageable);

}
