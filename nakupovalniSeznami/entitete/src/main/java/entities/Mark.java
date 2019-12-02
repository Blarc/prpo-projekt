package entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "mark")
@Table(name = "marks")
@NamedQueries(value =
        {
                @NamedQuery(name = "Mark.getAll", query = "SELECT m FROM mark m")
        })
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="address")
    private String address;

    @Column(name="description")
    private String description;

    @JsonbTransient
    @ManyToMany(mappedBy="marks")
    private List<ShoppingList> shoppingLists;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
}
