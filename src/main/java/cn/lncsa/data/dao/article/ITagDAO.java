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
     * Get a tag by title
     *
     * @param name tag name
     * @return
     */
    Tag getByTitle(String name);
}
