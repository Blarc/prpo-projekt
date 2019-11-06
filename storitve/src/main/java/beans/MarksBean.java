package beans;

import entities.Mark;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<Mark> getAllMarks(){

        return em.createNamedQuery("Mark.getAll", Mark.class).getResultList();
    }
}