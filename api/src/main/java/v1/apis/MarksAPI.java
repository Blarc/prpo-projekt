package v1.apis;

import beans.MarksBean;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.Mark;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("marks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarksAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private MarksBean marksBean;

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(marksBean.getAll(queryParams))
                .header("X-Total-Count", marksBean.getAllCount(queryParams))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @GET
    @Path("{id}")
    public Response getMark(@PathParam("id") Integer id) {
        Mark mark = marksBean.get(id);
        if (mark != null) {
            return Response.ok(mark).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @POST
    public Response addMark(Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.add(mark))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @PUT
    @Path("{id}")
    public Response updateMark(@PathParam("id") Integer id, Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.update(id, mark))
                .build();
    }

    // TODO fdemsar @Operation annotation (glej UsersAPI)
    @DELETE
    @Path("{id}")
    public Response deleteMark(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.remove(id))
                .build();
    }
}