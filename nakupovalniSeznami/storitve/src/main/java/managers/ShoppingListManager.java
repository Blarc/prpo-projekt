package managers;

import beans.ItemsBean;
import beans.ShoppingListsBean;
import beans.UsersBean;
import dtos.ShoppingListDto;
import entities.Item;
import entities.ShoppingList;
import entities.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class ShoppingListManager {

    private Logger log = Logger.getLogger(ShoppingListManager.class.getName());

    @Inject
    private UsersBean usersBean;

    @Inject
    private ItemsBean itemsBean;

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

    private ShoppingList createShoppingListFromDto(ShoppingListDto shoppingListDto) {

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

        return shoppingList;
    }

    public ShoppingList createShoppingList(ShoppingListDto shoppingListDto) {

        ShoppingList shoppingList = createShoppingListFromDto(shoppingListDto);

        if (shoppingList == null) {
            return null;
        }

        return shoppingListsBean.add(shoppingList);
    }

    public ShoppingList updateShoppingList(int id, ShoppingListDto shoppingListDto) {

        ShoppingList shoppingList = createShoppingListFromDto(shoppingListDto);

        if (shoppingList == null) {
            return null;
        }

        return shoppingListsBean.update(id, shoppingList);
    }

    public ShoppingList addItemToShoppingList(ShoppingList shoppingList, Integer itemId) {

        Item item = itemsBean.get(itemId);

        if (item == null) {
            log.warning("Can't add item to shopping list. Item does not exist.");
            return null;
        }

        shoppingList.getItems().add(item);
        shoppingListsBean.update(shoppingList.getId(), shoppingList);
        return shoppingList;
    }

    public ShoppingList removeItemFromShoppingList(ShoppingList shoppingList, Integer itemId) {

        Item item = itemsBean.get(itemId);

        if (item == null) {
            log.warning("Can't remove item from the shopping list. Item does not exit.");
            return shoppingList;
        }

        if (shoppingList.getItems().remove(item)) {
            shoppingListsBean.update(shoppingList.getId(), shoppingList);
        } else {
            log.warning("Can't remove item from the shopping list. Item is not part of the shopping list.");
        }

        return shoppingList;
    }
}
