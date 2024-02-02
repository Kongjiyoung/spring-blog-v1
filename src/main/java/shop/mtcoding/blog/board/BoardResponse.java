package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {


    //bt.id, bt.content, bt.title, bt.created_at, bt.user_id, ut.username
    @AllArgsConstructor
    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private Timestamp createAt;

        private Integer userId;

        private String username;
    }
}
