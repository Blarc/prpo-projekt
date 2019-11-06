package beans;

import entities.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    public List<User> getAll() {

        return em.createNamedQuery("User.getAll", User.class).getResultList();
    }

    public User get(int userId) {
        return em.find(User.class, userId);
    }

    @Transactional
    public User add(User u) {
        if (u != null) {
            em.persist(u);
        }
        return u;
    }

    @Transactional
    public void update(int id, User u) {
        User old = get(id);
        u.setId(old.getId());
        em.merge(u);
    }

    @Transactional
    public Integer remove(int id) {
        User u = get(id);
        if (u != null) {
            em.remove(u);
        }

        return id;
    }
}