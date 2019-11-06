package managers;

import beans.ShoppingListsBean;
import beans.UsersBean;
import dtos.ShoppingListDto;
import entities.ShoppingList;
import entities.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ShoppingListManager {

    private Logger log = Logger.getLogger(ShoppingListManager.class.getName());

    @Inject
    private UsersBean usersBean;

    @Inject
    private ShoppingListsBean shoppingListsBean;

    private String idBean;

    @PostConstruct
    private void init() {
        idBean = UUID.randomUUID().toString();
        log.info("Incializacija zrna " + ShoppingListManager.class.getName() + " " + idBean);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + ShoppingListManager.class.getName() + " " + idBean);
    }

    public ShoppingList createShoppingList(ShoppingListDto shoppingListDto) {

        User user = usersBean.get(shoppingListDto.getUserId());

        if (user == null) {
            log.warning("Can't create a new shopping list. User does not exist.");
            return null;
        }

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setName(shoppingListDto.getName());
        shoppingList.setDescription(shoppingListDto.getDescription());
        shoppingList.setTimeCreated(Instant.now());

        return shoppingListsBean.add(shoppingList);
    }
}
