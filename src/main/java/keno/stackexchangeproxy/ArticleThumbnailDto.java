package keno.stackexchangeproxy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleThumbnailDto {
    private Long id = 0L;
    private String title = "";
    private String author = "";
    private String createdAt = "";
    private String content = "";
    private int numComments = 0;
    private int numAnswers = 0;
    private int numLikes = 0;
    private int numViews = 0;
    private List<String> tags = new ArrayList<>();
}
