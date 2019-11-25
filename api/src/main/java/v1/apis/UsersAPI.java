package v1.apis;

import beans.UsersBean;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UsersBean usersBean;

    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(usersBean.getAll(queryParams))
                .header("X-Total-Count", usersBean.getAllCount(queryParams))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id) {
        User user = usersBean.get(id);

        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addUser(User user) {
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.add(user))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.update(id, user))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.remove(id))
                .build();
    }
}
