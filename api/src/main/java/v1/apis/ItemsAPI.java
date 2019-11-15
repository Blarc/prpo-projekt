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
        Item ajtm = itemsBean.get(id);

        if (ajtm != null) {
            return Response.ok(ajtm).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addItem(Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.add(item))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") Integer id, Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.update(id, item))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(itemsBean.remove(id))
                .build();
    }
}
