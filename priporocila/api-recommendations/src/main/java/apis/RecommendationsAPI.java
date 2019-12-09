package apis;

import entities.Item;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Path("recommendations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationsAPI {

    private Map<Item, Integer> recommendations;

    @PostConstruct
    private void init() {
        recommendations = new HashMap<>();
        recommendations.put(new Item(1, "Mleko", ""), 4);
        recommendations.put(new Item(2, "Kruh", ""), 6);
        recommendations.put(new Item(3, "Sir", ""), 3);
    }

    @GET
    public Response getAll() {
        return Response.ok(
            recommendations
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        e -> e.getKey().getId(),
                        Map.Entry::getValue,
                        (e1, e2) -> e1 , LinkedHashMap::new
                ))
        ).build();
    }

    @POST
    public Response addItem(Item item) {
        // TODO fdemsar exception
        return Response
                .status(Response.Status.OK)
                .entity(recommendations.put(item, recommendations.get(item) + 1))
                .build();
    }

}
