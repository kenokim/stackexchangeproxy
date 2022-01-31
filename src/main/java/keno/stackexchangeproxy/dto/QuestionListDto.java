package keno.stackexchangeproxy.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data @ToString
public class QuestionListDto implements Serializable {
    private List<ItemDto> items;
    private boolean has_more;
    private int backoff;
    private int quota_max;
    private int quota_remaining;
    private int page;
    private int page_size;
    private Long total;
    private String type;

}
