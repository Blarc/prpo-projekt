package beans;

import entities.Item;
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
public class ItemsBean {

    private final Logger log = Logger.getLogger(beans.ItemsBean.class.getName());
    private String idBean;

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Incializacija zrna " + beans.ItemsBean.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + beans.ItemsBean.class.getName() + " " + idBean);
    }

    public List<Item> getAll() {

        return em.createNamedQuery("Item.getAll", Item.class).getResultList();
    }

    public Item get(int itemId){ return em.find(Item.class, itemId);}

    @Transactional
    public Item add(Item a) {
        if (a != null) {
            em.persist(a);
        }
        return a;
    }

    @Transactional
    public Item update(int id, Item a) {
        Item old = get(id);
        a.setId(old.getId());
        em.merge(a);
        return a;
    }

    @Transactional
    public Integer remove(int id) {
        Item a = get(id);
        if (a != null) {
            em.remove(a);
        }
        return id;
    }
}
