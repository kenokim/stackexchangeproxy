package keno.stackexchangeproxy;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @Builder
public class QuestionDto implements Serializable {
    private List<String> tags = new ArrayList<>();
    private String author;
    private int numViews;
    private int numLikes;

}
