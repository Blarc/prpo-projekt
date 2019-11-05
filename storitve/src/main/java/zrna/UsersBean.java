package zrna;

import entities.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UsersBean {

    private final Logger log = Logger.getLogger(UsersBean.class.getName());
    private String idBean;

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Inicializacija zrna " + UsersBean.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UsersBean.class.getName() + " " + idBean);
    }

    public List<User> getAllUsers() {

        return em.createNamedQuery("User.getAll", User.class).getResultList();
    }
}