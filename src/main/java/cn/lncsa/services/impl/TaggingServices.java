package cn.lncsa.services.impl;

import cn.lncsa.common.ListTools;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.dao.article.ITagDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.ITaggingServices;
import cn.lncsa.services.exceptions.TaggingOperateException;
import cn.lncsa.services.helper.RelationshipHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cattenlinger on 2016/9/29.
 */
@Service
public class TaggingServices implements ITaggingServices {
    private ITagDAO tagDAO;
    private ITagArticleDAO tagArticleDAO;
    private IArticleDAO articleDAO;

    private RelationshipHelper<Article,Tag,ArticleTag> relationshipHelper;

    public TaggingServices() {
        relationshipHelper = new RelationshipHelper<>();
    }

    @Autowired
    public void setTagDAO(ITagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Autowired
    public void setTagArticleDAO(ITagArticleDAO tagArticleDAO) {
        this.tagArticleDAO = tagArticleDAO;
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public Tag save(Tag tag) {
        return tagDAO.save(tag);
    }

    @Override
    public void delete(Integer tagId){
        tagArticleDAO.delete(tagArticleDAO.getRelationships(tagDAO.findOne(tagId)));
        tagDAO.delete(tagId);
    }

    @Override
    public void taggingArticle(Integer articleId, List<Tag> tagList) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        relationshipHelper.updateRelationship(
                articleDAO.findOne(articleId),
                ListTools.listDiff(tagDAO.getByArticle(articleDAO.findOne(articleId)),tagList),
                tagArticleDAO);
    }

    @Override
    public void removeTagFromArticle(Integer articleId, List<Tag> tagList){
        tagArticleDAO.delete(tagArticleDAO.getRelationships(articleDAO.findOne(articleId),tagList));
    }

    @Override
    public void removeAllTagsFromArticle(Integer articleId) {
        tagArticleDAO.removeAllByArticleId(articleId);
    }

    @Override
    public Tag get(Integer tagId) {
        return tagDAO.findOne(tagId);
    }

    @Override
    public List<Tag> get(List<Integer> tagIds) {
        return tagDAO.findAll(tagIds);
    }

    @Override
    public List<Tag> getByName(List<String> tagName) {
        return tagDAO.getByTitle(tagName);
    }

    @Override
    public List<Tag> queryByArticleId(Integer articleId){
        return tagDAO.getByArticle(articleDAO.findOne(articleId));
    }

    @Override
    public Page<Article> queryArticlesUnderTag(Integer tagId, List<String> status, Pageable pageable){
        return articleDAO.getByTags(Arrays.asList(tagDAO.findOne(tagId)),status,pageable);
    }

    @Override
    public Page<Tag> getAll(Pageable pageable) {
        return tagDAO.findAll(pageable);
    }

    /*
    * Private procedure
    *
    * */

}
