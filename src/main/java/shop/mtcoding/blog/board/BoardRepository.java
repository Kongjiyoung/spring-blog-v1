//package shop.mtcoding.blog.board;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Repository
//public class BoardRepository {
//    private final EntityManager em;
//
//    public List<Board> findAll(int page){
//        final int COUNT=3;
//        int value = page*COUNT;
//        Query query = em.createNativeQuery("select*from board_tb order by id desc limit ?,?", Board.class);
//        query.setParameter(1, value);
//        query.setParameter(2, COUNT);
//
//        List<Board> boardList = query.getResultList(); //게시글을 여러건을 받기위해
//        return boardList;
//    }
//}
package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(int page){ // 모든 걸 다 조회
        final int COUNT = 3;
        int value = page*COUNT;

        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, COUNT);

        List<Board> boardList = query.getResultList(); //여러건
        return boardList;

    }

    public int totalCount(){ // 모든 걸 다 조회

        Query query = em.createNativeQuery("select count(*) from board_tb");

        int count= ((Number)query.getSingleResult()).intValue();
        System.out.println("count : "+count);
        return count;

    }
}
