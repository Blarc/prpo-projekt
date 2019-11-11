package beans;

import entities.ShoppingList;

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
public class ShoppingListsBean {

    private final Logger log = Logger.getLogger(ShoppingListsBean.class.getName());
    private String idBean;

    @PersistenceContext(unitName = "nakupovalni-seznami")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Incializacija zrna " + ShoppingListsBean.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + ShoppingListsBean.class.getName() + " " + idBean);
    }

    public List<ShoppingList> getAll() {

        return em.createNamedQuery("ShoppingList.getAll", ShoppingList.class).getResultList();
    }


    @Transactional
    public ShoppingList add(ShoppingList u) {
        if (u != null) {
            em.persist(u);
        }
        return u;
    }

    // TODO fdemsar CRUD operacije (glej UsersBean)
}
