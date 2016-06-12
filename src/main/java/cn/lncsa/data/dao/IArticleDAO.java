package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by catte on 2016/6/12.
 */
public interface IArticleDAO extends PagingAndSortingRepository<Article,Integer> {
}
