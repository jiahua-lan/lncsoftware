package cn.lncsa.services;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.exceptions.TaggingOperateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by cattenlinger on 2016/9/27.
 */
public interface ITaggingServices {

    /**
     * Add a new tag or save an exist tag
     *
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * Delete a tag an remove this tag from all article
     * <p>
     * Normally, just stay an empty tag here is best
     *
     * @param tagId
     * @return
     */
    void deleteTag(Integer tagId) throws TaggingOperateException;

    /*
    *
    * Operation to Article-Tag relationship
    *
    * */

    /**
     * Tagging an article with a list of tags
     *
     * @param tagList
     * @param articleId
     * @return
     */
    void taggingArticle(Integer articleId, List<Tag> tagList) throws TaggingOperateException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Un-tagging an article with a list of tags
     *
     * @param tagList
     * @param articleId
     */
    void removeTagFromArticle(Integer articleId, List<Tag> tagList) throws TaggingOperateException;

    /*
    *
    * Query methods
    *
    * */

    /**
     * Get a tag object by id
     *
     * @param tagId
     * @return
     */
    Tag getTagById(Integer tagId);

    /**
     * Query a list of tags using a list of tag-id
     *
     * @param tagIds
     * @return
     */
    List<Tag> getListOfTagById(List<Integer> tagIds);


    /**
     * Query a list of tags using a list of tag-name
     *
     * @param tagName
     * @return
     */
    List<Tag> getListOfTagByName(List<String> tagName);

    /**
     * Get tags those tagged to an article
     *
     * @param articleId
     * @return
     */
    List<Tag> queryByArticleId(Integer articleId) throws TaggingOperateException;

    /**
     * Query articles those tagged by a same tag.
     *
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Article> queryArticlesUnderTag(Integer tagId, List<String> status, Pageable pageable) throws TaggingOperateException;

    /**
     * List all tags
     *
     * @param pageable
     * @return
     */
    Page<Tag> getAllTags(Pageable pageable);
}
