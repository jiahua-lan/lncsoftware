package cn.lncsa.services;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/27.
 */
public interface TaggingServices {

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
    Tag deleteTag(Integer tagId);

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
    ArticleTag taggingArticle(List<Tag> tagList, Integer articleId);

    /**
     * Tagging an article with single tag
     *
     * @param tagId
     * @param articleId
     * @return
     */
    ArticleTag addATagToArticle(Integer tagId, Integer articleId);

    /**
     * Un-tagging an article with a list of tags
     *
     * @param tagList
     * @param articleId
     */
    void removeTagFromArticle(List<Tag> tagList, Integer articleId);

    /**
     * Un-tagging an article with single tag
     *
     * @param tagId
     * @param articleId
     */
    void removeATagFromArticle(Integer tagId, Integer articleId);

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
     * Get tags those tagged to an article
     *
     * @param articleId
     * @return
     */
    List<Tag> queryByArticleId(Integer articleId);

    /**
     * Query articles those tagged by a same tag.
     *
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Article> queryArticlesUnderTag(Integer tagId, Pageable pageable);

    /**
     * List all tags
     *
     * @param pageable
     * @return
     */
    Page<Tag> getAllTags(Pageable pageable);
}
