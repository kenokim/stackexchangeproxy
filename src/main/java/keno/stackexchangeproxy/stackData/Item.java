package keno.stackexchangeproxy.stackData;

import keno.stackexchangeproxy.dto.AnswerStackDto;
import keno.stackexchangeproxy.dto.CommentStackDto;
import keno.stackexchangeproxy.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Item implements Serializable {

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CommentStack> comments = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<AnswerStack> answers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private Owner owner;

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

    @Id
    private Long question_id;
    private String content_license;

    @Column(columnDefinition = "TEXT")
    private String link;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    protected Item() {}

    public static Item createItem(ItemDto dto, Owner owner) {
        return Item.builder()
                .accepted_answer_id(dto.getAccepted_answer_id())
                .answer_count(dto.getAnswer_count())
                .owner(owner)
                .body(dto.getBody())
                .close_vote_count(dto.getClose_vote_count())
                .is_answered(dto.is_answered())
                .question_id(dto.getQuestion_id())
                .tags(dto.getTags())
                .link(dto.getLink())
                .accepted_answer_id(dto.getAccepted_answer_id())
                .last_activity_date(dto.getLast_activity_date())
                .last_edit_date(dto.getLast_edit_date())
                .creation_date(dto.getCreation_date())
                .comments(new ArrayList<>())
                .answers(new ArrayList<>())
                .build();
    }

    public AnswerStack addAnswer(AnswerStackDto dto, Owner owner) {
        AnswerStack a = AnswerStack.createAnswerStack(dto, owner);
        a.setItem(this);
        this.answers.add(a);
        return a;
    }

    public CommentStack addComment(CommentStackDto dto, Owner owner) {
        CommentStack c = CommentStack.createCommentStack(dto, owner);
        this.comments.add(c);
        c.setItem(this);
        return c;
    }
}

