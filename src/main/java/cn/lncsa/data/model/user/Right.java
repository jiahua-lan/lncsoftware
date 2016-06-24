package cn.lncsa.data.model.user;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "rights")
public class Right {
    private Integer id;
    private String name;
    private String color;
    private String url;

    public Right(){

    }

    public Right(String name) {
        this.name = name;
    }

    public Right(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
