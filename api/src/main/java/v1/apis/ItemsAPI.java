package v1.apis;

import beans.ItemsBean;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemsAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ItemsBean itemsBean;

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(itemsBean.getAll(queryParams))
                .header("X-Total-Count", itemsBean.getAllCount(queryParams))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @GET
    @Path("{id}")
    public Response getItem(@PathParam("id") Integer id) {
        Item item = itemsBean.get(id);
        if (item != null) {
            return Response.ok(item).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @POST
    public Response addItem(Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.add(item))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") Integer id, Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.update(id, item))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.remove(id))
                .build();
    }
}
