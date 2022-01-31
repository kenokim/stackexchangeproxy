package keno.stackexchangeproxy.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class AnswerStackDto {
    private OwnerDto owner;
    private List<String> tags = new ArrayList<>();
    private List<CommentStackDto> comments = new ArrayList<>();
    private boolean is_accepted;
    private int score;
    private Long last_activity_date;
    private Long creation_date;
    private Long answer_id;
    private Long question_id;
    private String link;
    private String title;
    private String body;

}
