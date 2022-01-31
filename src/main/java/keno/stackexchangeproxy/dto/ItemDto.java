package keno.stackexchangeproxy.dto;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @ToString
public class ItemDto implements Serializable {

    private List<String> tags = new ArrayList<>();
    private List<CommentStackDto> comments = new ArrayList<>();
    private List<AnswerStackDto> answers = new ArrayList<>();
    private OwnerDto owner;
    private int delete_vote_count;
    private int reopen_vote_count;
    private int close_vote_count;
    private boolean is_answered;
    private int view_count;
    private int favorite_count;
    private Long accepted_answer_id;
    private int answer_count;
    private int score;
    private Long last_activity_date;
    private Long creation_date;
    private Long last_edit_date;
    private Long question_id;
    private String content_license;
    private String link;
    private String title;
    private String body;
}

