package zrna;

import entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UsersBean {

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    public List<User> getUsers() {

        return em.createQuery("SELECT u FROM userA u ").getResultList();
    }
}