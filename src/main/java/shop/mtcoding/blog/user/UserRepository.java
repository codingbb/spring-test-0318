package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User updateById(Integer id, UserRequest.SaveDTO requestDTO) {
        User user = findById(id);
        user.update(requestDTO);
        return user;
    }


    public User findById(Integer id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = """
                select u from User u where u.username = :username and u.password = :password
                """;
        Query query = em.createQuery(q, User.class);
        query.setParameter("username", requestDTO.getUsername());
        query.setParameter("password", requestDTO.getPassword());
        User user = (User) query.getSingleResult();
        return user;
    }


}
