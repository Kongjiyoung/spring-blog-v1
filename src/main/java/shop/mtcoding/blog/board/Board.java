package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.time.LocalDateTime;

@Data //getter,setter,toString
@Entity
@Table(name = "board_tb")
public class Board {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto
    private int id;
    private String title;
    private String content;
    //private int userId; //폴링키에 제약조건은 안넣는게 좋음

    @ManyToOne  //many는  one은 유저
    private User user; //ORM할려고 보드와 유저를 조인해서 만들면 여기 담아준다

    @CreationTimestamp
    private LocalDateTime createdAt;
}