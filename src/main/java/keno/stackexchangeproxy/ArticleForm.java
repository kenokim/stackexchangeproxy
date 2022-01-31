package keno.stackexchangeproxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ArticleForm {
    private String title;
    private String content;
    private String tags;
    private LocalDateTime dateTime;

    public List<String> getTagList() {
        List<String> tagList = new ArrayList<>();
        Arrays.stream(this.tags.split(" |,")).distinct().forEach(t -> { tagList.add(t); });
        return tagList;
    }

}
