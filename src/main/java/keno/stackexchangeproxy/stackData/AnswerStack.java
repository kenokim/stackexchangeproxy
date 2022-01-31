package keno.stackexchangeproxy.stackData;

import keno.stackexchangeproxy.dto.AnswerStackDto;
import keno.stackexchangeproxy.dto.CommentStackDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
public class AnswerStack {

    @OneToOne
    @JoinColumn(name = "user_id")
    private Owner owner;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post_id")
    private List<CommentStack> comments = new ArrayList<>();

    private boolean is_accepted;
    private int score;
    private Long last_activity_date;
    private Long creation_date;

    @Id
    private Long answer_id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Item item;


    @Column(columnDefinition = "TEXT")
    private String link;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    protected AnswerStack() {}

    public static AnswerStack createAnswerStack(AnswerStackDto dto, Owner owner) {
        return AnswerStack.builder()
                .answer_id(dto.getAnswer_id())
                .body(dto.getBody())
                .owner(owner)
                .creation_date(dto.getCreation_date())
                //.tags(dto.getTags())
                .title(dto.getTitle())
                .score(dto.getScore())
                .build();
    }

    public CommentStack addComment(CommentStackDto dto, Owner owner) {
        CommentStack c = CommentStack.createCommentStack(dto, owner);
        this.comments.add(c);
        c.setAnswer(this);
        return c;
    }
}
