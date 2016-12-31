package cn.lncsa.services;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by cattenlinger on 2016/9/27.
 */
public interface ITaggingServices {

    /**
     * Add a new topic or save an exist topic
     *
     * @param topic
     * @return
     */
    Topic save(Topic topic);

    /**
     * Delete a tag an remove this tag from all article
     * <p>
     * Normally, just stay an empty tag here is best
     *
     * @param tagId
     * @return
     */
    void delete(Integer tagId);

    /*
    *
    * Operation to Article-Topic relationship
    *
    * */

    /**
     * Tagging an article with a list of tags
     *
     * @param topicList
     * @param articleId
     * @return
     */
    void taggingArticle(Integer articleId, List<Topic> topicList) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Un-tagging an article with a list of tags
     *
     * @param topicList
     * @param articleId
     */
    void removeTagFromArticle(Integer articleId, List<Topic> topicList);

    /**
     * Remove all relationships wired to an article
     *
     * @param articleId
     */
    void removeAllTagsFromArticle(Integer articleId);

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
    Topic get(Integer tagId);

    /**
     * Query a list of tags using a list of tag-id
     *
     * @param tagIds
     * @return
     */
    List<Topic> get(List<Integer> tagIds);


    /**
     * Query a list of tags using a list of tag-name
     *
     * @param tagName
     * @return
     */
    List<Topic> getByName(List<String> tagName);

    /**
     * Get tags those tagged to an article
     *
     * @param articleId
     * @return
     */
    List<Topic> queryByArticleId(Integer articleId);

    /**
     * Query articles those tagged by a same tag.
     *
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Article> queryArticlesUnderTag(Integer tagId, List<String> status, Pageable pageable);

    /**
     * List all tags
     *
     * @param pageable
     * @return
     */
    Page<Topic> getAll(Pageable pageable);
}
