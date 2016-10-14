package cn.lncsa.data.dao.article;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface ITagDAO extends JpaRepository<Tag,Integer> {
    /**
     * Get tags by title
     *
     * @param names tag name
     * @return
     */
    @Query("select t from Tag t where t.title in ?1")
    List<Tag> getByTitle(List<String> names);
}
