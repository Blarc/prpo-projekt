package v1.apis;

import beans.ShoppingListsBean;
import com.kumuluz.ee.rest.beans.QueryParameters;
import dtos.ShoppingListDto;
import entities.ShoppingList;
import managers.ShoppingListManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("shoppingLists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShoppingListsAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ShoppingListManager sm;

    @Inject
    private ShoppingListsBean shoppingListsBean;

    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(shoppingListsBean.getAll(queryParams))
                .header("X-Total-Count", shoppingListsBean.getAllCount(queryParams))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getShoppingList(@PathParam("id") Integer id) {
        ShoppingList shoppingList = shoppingListsBean.get(id);
        if (shoppingList != null) {
            return Response.ok(shoppingList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addShoppingList(ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = sm.createShoppingList(shoppingListDto);
        if (shoppingList == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.add(shoppingList))
                .build();

    }

    @PUT
    @Path("{id}")
    public Response updateShoppingList(@PathParam("id") Integer id, ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = sm.updateShoppingList(id, shoppingListDto);
        if (shoppingList == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.update(id, shoppingList))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteShoppingList(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.remove(id))
                .build();
    }
}