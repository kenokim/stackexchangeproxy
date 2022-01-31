package keno.stackexchangeproxy.dto;

import lombok.Data;
import lombok.ToString;


@Data @ToString
public class CommentStackDto {
    private OwnerDto owner;
    private boolean edited;
    private int score;
    private Long creation_date;
    private Long post_id;
    private Long comment_id;
    private String body;
}
