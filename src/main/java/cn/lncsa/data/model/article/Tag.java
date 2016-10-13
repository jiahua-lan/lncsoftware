package cn.lncsa.data.model.article;

import cn.lncsa.data.model.base.IBaseModel;
import cn.lncsa.data.model.domain.IRelationSlave;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "tags")
public class Tag implements IRelationSlave<Integer> {
    private Integer id;
    private String title;

    public Tag() {
    }

    public Tag(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Column(unique = true)
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return (title+id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(obj.getClass()) &&
                ((Tag)obj).getTitle().equals(title) &&
                (((Tag) obj).getId() == id);
    }
}
