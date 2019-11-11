package v1.apis;

import beans.MarksBean;
import entities.Mark;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("marks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarksAPI {

    @Inject
    private MarksBean marksBean;

    @GET
    public Response getAll() {
        return Response.ok(marksBean.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getMark(@PathParam("id") Integer id) {
        Mark mark = marksBean.get(id);
        if (mark != null) {
            return Response.ok(mark).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addMark(Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.add(mark))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateMark(@PathParam("id") Integer id, Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.update(id, mark))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMark(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.remove(id))
                .build();
    }
}