package v1.apis;

import beans.ItemsBean;
import entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemsAPI {

    @Inject
    private ItemsBean itemsBean;

    @GET
    public Response getAll() {
        return Response.ok(itemsBean.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getItem(@PathParam("id") Integer id) {
        // TODO fdemsar
        return null;
    }

    @POST
    public Response addItem(Item item) {
        // TODO fdemsar
        return null;
    }

    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") Integer id) {
        // TODO fdemsar
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id) {
        // TODO fdemsar
        return null;
    }
}
