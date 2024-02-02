package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board findById(int id){
        Query query=em.createQuery("select b from Board b join fetch b.user u where b.id= :id", Board.class); //b는 보드객체의 필드  :는 ?로 받을 수 있다
        query.setParameter("id",id);

        Board board = (Board) query.getSingleResult();
        return board;
    }
}
