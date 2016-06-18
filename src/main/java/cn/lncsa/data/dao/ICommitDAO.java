package cn.lncsa.data.dao;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Commit;
import cn.lncsa.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by catte on 2016/6/12.
 */
public interface ICommitDAO extends JpaRepository<Commit,Integer> {

}
