package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;

    }

    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }

    }

}
