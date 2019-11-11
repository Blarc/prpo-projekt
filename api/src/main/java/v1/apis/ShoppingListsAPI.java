package v1.apis;

import beans.ShoppingListsBean;
import entities.ShoppingList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("shoppingLists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShoppingListsAPI {

    @Inject
    private ShoppingListsBean shoppingListsBean;

    @GET
    public Response getAll() {
        return Response.ok(shoppingListsBean.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getShoppingList(@PathParam("id") Integer id) {
        // TODO fdemsar
        return null;
    }

    @POST
    public Response addShoppingList(ShoppingList shoppingList) {
        // TODO fdemsar
        return null;
    }

    @PUT
    @Path("{id}")
    public Response updateShoppingList(@PathParam("id") Integer id, ShoppingList shoppingList) {
        // TODO fdemsar
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response deleteShoppingList(@PathParam("id") Integer id) {
        // TODO fdemsar
        return null;
    }
}