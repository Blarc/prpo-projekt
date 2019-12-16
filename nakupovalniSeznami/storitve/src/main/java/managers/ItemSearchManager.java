package managers;

import beans.ItemsBean;
import dtos.WordSimilarityDto;
import entities.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ItemSearchManager {

    private Client httpClient;

    private static final String apiUrl = "https://text-similarity-calculator.p.rapidapi.com/stringcalculator.php";
    private static final String host = "text-similarity-calculator.p.rapidapi.com";
    private static final String key = "95c66fbb65mshf98f419aac4c28fp12aab5jsn5436ec285da4";

    @Inject
    private ItemsBean itemsBean;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public Item getByName(String name) {
        List<Item> allItems = itemsBean.getAll();
        return allItems.stream()
                .max(Comparator.comparingInt(item -> compareWords(item.getName(), name)))
                .orElse(null);
    }

    private int compareWords(String word1, String word2) {
        return Math.round(httpClient
                .target(apiUrl)
                .queryParam("ftext", word1)
                .queryParam("stext", word2)
                .request()
                .header("x-rapidapi-host", host)
                .header("x-rapidapi-key", key)
                .get(new GenericType<WordSimilarityDto>(){})
                .getPercentage()
        );
    }
}
