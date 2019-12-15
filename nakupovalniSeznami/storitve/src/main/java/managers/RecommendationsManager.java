package managers;

import beans.ItemsBean;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import entities.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class RecommendationsManager {

    @Inject
    private ItemsBean itemsBean;

    private Client httpClient;

    private String baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance().get("kumuluzee.server.base-url").orElse("http://localhost/servlet");
    }

    public List<Item> getRecommendations() {
        return httpClient
                .target(baseUrl)
                .request()
                .get(new GenericType<List<Integer>>() {
                })
                .stream()
                .map(i -> itemsBean.get(i))
                .collect(Collectors.toList());
    }
}
