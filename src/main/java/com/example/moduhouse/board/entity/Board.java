package com.example.moduhouse.board.entity;

import com.example.moduhouse.board.dto.BoardRequestDto;
import com.example.moduhouse.comment.entity.Comment;
import com.example.moduhouse.global.entity.Timestamped;
import com.example.moduhouse.user.entity.User;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @OrderBy("createdAt DESC")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardLike> boardLikeList = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column
    private String local;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Board(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.username = user.getUsername();
        this.contents = requestDto.getContent();
        this.local = requestDto.getLocal();
        this.user = user;
    }

    public void update(BoardRequestDto boardrequestDto) {
        this.title = boardrequestDto.getTitle();
        this.local = boardrequestDto.getLocal();
        this.contents = boardrequestDto.getContent();
    }
}