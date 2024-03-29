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

        recommendations.put(new Item(1, "Eggs", "Egg yolks and whole eggs store significant amounts of protein and choline, and are widely used in cookery."), 4);
        recommendations.put(new Item(2, "Milk", "Milk is a nutrient-rich, white liquid food produced by the mammary glands of mammals."), 6);
        recommendations.put(new Item(3, "Ham", "Ham is pork from a leg cut that has been preserved by wet or dry curing, with or without smoking."), 3);
        recommendations.put(new Item(4, "Bread", "Bread is a staple food prepared from a dough of flour and water, usually by baking."), 11);
    }

    @GET
    public Response getAll() {
        return Response.ok(
                recommendations
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Item, Integer>comparingByValue().reversed())
                    .map(e -> e.getKey().getId())
                    .collect(Collectors.toList())
        ).build();
    }

    @POST
    public Response addItem(Item item) {
        if(item == null){
            return Response
                    .status(Response.Status.NOT_ACCEPTABLE)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(recommendations.put(item, recommendations.get(item) + 1))
                .build();
    }

}
