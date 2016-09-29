package cn.lncsa.data.validator;

import cn.lncsa.data.model.article.Article;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by cattenlinger on 2016/9/27.
 */
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Article.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "authorId", null, "author is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "context", null, "content is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", null, "status is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", null, "title is empty");

        Article article = (Article) o;
        if(article.getTitle().length() > 60) errors.rejectValue("title",null,"title too long");
        if(article.getPreviewSentences().length() > 120) errors.rejectValue("previewSentences",null,"preview sentences too long");
    }
}
