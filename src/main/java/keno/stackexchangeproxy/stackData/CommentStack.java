package keno.stackexchangeproxy.stackData;

import keno.stackexchangeproxy.dto.AnswerStackDto;
import keno.stackexchangeproxy.dto.CommentStackDto;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
public class CommentStack {

    @OneToOne
    @JoinColumn(name = "user_id")
    private Owner owner;

    private boolean edited;
    private int score;
    private Long creation_date;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private AnswerStack answer;

    private Long post_id;

    @Id
    private Long comment_id;

    @Column(columnDefinition = "TEXT")
    private String body;

    protected CommentStack() {}

    public static CommentStack createCommentStack(CommentStackDto dto, Owner owner) {
        return CommentStack.builder()
                .body(dto.getBody())
                .comment_id(dto.getComment_id())
                .creation_date(dto.getCreation_date())
                .owner(owner)
                .edited(dto.isEdited())
                .score(dto.getScore())
                .post_id(dto.getPost_id())
                .build();
    }
}
