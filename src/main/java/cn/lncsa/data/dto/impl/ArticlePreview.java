package cn.lncsa.data.dto.impl;

import cn.lncsa.data.dto.IArticlePreview;

import java.util.Date;

/**
 * Created by cattenlinger on 16/9/12.
 */
public class ArticlePreview implements IArticlePreview {

    private String author;
    private Date date;
    private String content;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
