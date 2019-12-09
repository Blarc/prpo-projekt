package beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
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

    public List<ShoppingList> getAll(QueryParameters queryParams) {
        return JPAUtils.queryEntities(em, ShoppingList.class, queryParams);
    }

    public Long getAllCount(QueryParameters queryParams) {
        return JPAUtils.queryEntitiesCount(em, ShoppingList.class, queryParams);
    }

    public ShoppingList get(int shoppingListid) {
        return em.find(ShoppingList.class, shoppingListid);
    }

    @Transactional
    public ShoppingList add(ShoppingList a) {
        if (a != null) {
            em.persist(a);
        }
        return a;
    }

    @Transactional
    public ShoppingList update(int id, ShoppingList a) {
        ShoppingList old = get(id);
        a.setId(old.getId());
        em.merge(a);
        return a;
    }

    @Transactional
    public Integer remove(int id) {
        ShoppingList a = get(id);
        if (a != null) {
            em.remove(a);
        }
        return id;
    }
}
