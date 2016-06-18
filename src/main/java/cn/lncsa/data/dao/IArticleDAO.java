package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Commit;
import cn.lncsa.data.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface IArticleDAO extends PagingAndSortingRepository<Article,Integer> {
    /**
     * Find articles that match keyword in title
     *
     * @param keyword keyword
     * @return a list of articles that match keyword
     */
    Page<Article> findByTitleLike(String keyword, Pageable pageable);
}
