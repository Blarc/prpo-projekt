package beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import entities.Mark;

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
public class MarksBean {

    private final Logger log = Logger.getLogger(MarksBean.class.getName());
    private String idBean;

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Inicializacija zrna " + MarksBean.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + MarksBean.class.getName() + " " + idBean);
    }

    public List<Mark> getAll() {

        return em.createNamedQuery("Mark.getAll", Mark.class).getResultList();
    }

    public List<Mark> getAll(QueryParameters queryParams) {
        return JPAUtils.queryEntities(em, Mark.class, queryParams);
    }

    public Long getAllCount(QueryParameters queryParams) {
        return JPAUtils.queryEntitiesCount(em, Mark.class, queryParams);
    }

    public Mark get(int markId) {
        return em.find(Mark.class, markId);
    }

    @Transactional
    public Mark add(Mark m) {
        if (m != null) {
            em.persist(m);
        }
        return m;
    }

    @Transactional
    public Mark update(int id, Mark m) {
        Mark old = get(id);
        m.setId(old.getId());
        em.merge(m);
        return m;
    }

    @Transactional
    public Integer remove(int id) {
        Mark m = get(id);
        if (m != null) {
            em.remove(m);
        }
        return id;
    }
}