package cn.lncsa.data.dao;

import cn.lncsa.data.model.Right;
import cn.lncsa.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by catten on 16/6/12.
 */
public interface IRightDAO extends JpaRepository<Right,Integer>{

}
