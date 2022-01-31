package keno.stackexchangeproxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ArticleListDto implements Serializable {
    private Long totalNumArticles;
    private List<ArticleThumbnailDto> articles;
}
