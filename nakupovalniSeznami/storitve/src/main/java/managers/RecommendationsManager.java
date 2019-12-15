package managers;

import beans.ItemsBean;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class RecommendationsManager {

    @Inject
    private ItemsBean itemsBean;

    private Client httpClient;

    private String baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        // TODO this might not work
        baseUrl = ConfigurationUtil.getInstance().get("kumuluzee.server.base-url").orElse("http://localhost/servlet");
    }

    public LinkedList<Integer> getRecommendations() {
        return httpClient
                .target(baseUrl)
                .request()
                .get(new GenericType<LinkedList<Integer>>() {
        });
    }
}
