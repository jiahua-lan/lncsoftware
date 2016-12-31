package cn.lncsa.services;

import cn.lncsa.data.model.Bulletin;
import cn.lncsa.data.repository.IBulletinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by cattenlinger on 2017/1/1.
 */
@Service
public class BulletinServices{

    private IBulletinDAO bulletinDAO;

    @Autowired
    private void setBulletinDAO(IBulletinDAO bulletinDAO){
        this.bulletinDAO = bulletinDAO;
    }

    public void save(Bulletin bulletin) {
        bulletinDAO.save(bulletin);
    }

    public void delete(Integer bulletinId) {

    }

    public Bulletin get(Integer bulletinId) {
        return bulletinDAO.getOne(bulletinId);
    }

    public Page<Bulletin> get(String type, Pageable pageable) {
        return bulletinDAO.getByType(type,pageable);
    }

    public Page<Bulletin> get(Pageable pageable){
        return bulletinDAO.findAll(pageable);
    }

    public Bulletin getLatestOne(){
        return bulletinDAO.findAll(new PageRequest(0,1, Sort.Direction.DESC,"date")).getContent().get(0);
    }

    public List<String> getAllTags(){
        return bulletinDAO.getAllTags();
    }
}
