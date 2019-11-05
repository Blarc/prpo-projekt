package zrna;

import entities.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ItemsBean {

    private final Logger log = Logger.getLogger(zrna.ItemsBean.class.getName());
    private String idBean;

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Incializacija zrna " + zrna.ItemsBean.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + zrna.ItemsBean.class.getName() + " " + idBean);
    }

    public List<Item> getAllItems() {

        return em.createNamedQuery("Item.getAll", Item.class).getResultList();
    }
}
