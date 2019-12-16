package entities;

import javax.persistence.*;

@Entity(name="item")
@Table(name="items")
@NamedQueries(value =
        {
                @NamedQuery(name = "Item.getAll", query = "SELECT i FROM item i"),
                @NamedQuery(name = "Item.getById", query="SELECT i FROM item i  WHERE i.id = :id "),
                @NamedQuery(name = "Item.getShoppingList", query = "SELECT i.shoppingList FROM item i "),
                @NamedQuery(name = "Item.getDescription", query = "SELECT i.description FROM item i "),
                @NamedQuery(name = "Item.getByName", query = "SELECT i FROM item i WHERE i.name = :name ")
        })
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}