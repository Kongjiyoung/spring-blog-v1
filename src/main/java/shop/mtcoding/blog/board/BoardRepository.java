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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public BoardResponse.DetailDTO findById(int id) {
        //엔티티가 아닌 것은 JPA가 파싱안해준다.
        Query query = em.createNativeQuery("select bt.id, bt.title, bt.content, bt.created_at, bt.user_id, ut.username from Board_tb bt inner join user_tb ut on bt.user_id=ut.id  where bt.id=?");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO=rm.uniqueResult(query, BoardResponse.DetailDTO.class); //통신으로 받는데이터는 다 DTO 디비랑 통신하기 때문에
        //query.getSingleResult(); //리턴을 하는건 오브젝트 배열(object[])을 받아오기 때문에 (row하나가 오브젝트 배열) -> 그래서 rs[0]으로 하나씩 받아 다운캐스팅이 필요하다

        return responseDTO;
    }

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
