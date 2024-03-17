package shop.mtcoding.blog;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.board.BoardRequest;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findAll_lazyloading_test() {
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); //lazy loading
        });
    }

    @Test
    public void findByIdJoinUser_test() {
        Integer id = 1;
        boardRepository.findByIdJoinUser(id);

    }

    @Test
    public void updateById_test() {
        BoardRequest.UpdateDTO requestDTO = new BoardRequest.UpdateDTO();
        Integer id = 1;
        requestDTO.setTitle("title1");
        requestDTO.setContent("content1");
        requestDTO.setUsername("ssar");

        boardRepository.updateById(id, requestDTO);

        em.flush();

    }

    @Test
    public void deleteById_test() {
        Integer id = 1;

        boardRepository.deleteById(id);

        List<Board> boardList = boardRepository.findAll();
        Assertions.assertThat(boardList.size()).isEqualTo(3);

    }

    @Test
    public void findById_test() {
        Integer id = 3;
        Board board = boardRepository.findById(id);
        System.out.println(board);

        Assertions.assertThat(board.getContent()).isEqualTo("내용3");

    }


    @Test
    public void findAll_test() {
        List<Board> boardList = boardRepository.findAll();
        System.out.println("findAll_test : " + boardList.size());
        System.out.println("findAll_test : " + boardList.get(1).getTitle());
        System.out.println("findAll_test : " + boardList.get(1).getContent());

        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getContent()).isEqualTo("내용2");

    }

//    @Test
//    public void save_test() {
//       Board board = new Board("제목5", "내용5", "ssar");
//
//       boardRepository.save(board);
//        System.out.println("save_test : " + board);
//
//    }

}
