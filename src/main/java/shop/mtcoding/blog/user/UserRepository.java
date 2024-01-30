package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRepository { //= dao

    private EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

   @Transactional//org뭐시기 넣어야함
    public void save(UserRequest.JoinDTO requestDTO){
        Query query=em.createNativeQuery("insert into user_tb(username, password, email) values(?, ?, ?)"); //내가 쿼리를 직접짰고
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }

    @Transactional //org뭐시기 넣어야함
    public void saveV2(UserRequest.JoinDTO requestDTO){
        User user= new User(); //엔티티클래스에 옮겼음 //하이퍼네이트한테 클래스를 줌 하이퍼네이트가 insert문을 만들어서 넣어줌
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
        em.persist(user);
    }
}
