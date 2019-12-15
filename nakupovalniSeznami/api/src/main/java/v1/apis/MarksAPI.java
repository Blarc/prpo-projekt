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

    @Operation
            (description = "Returns list of marks.", summary = "List of marks",
                    tags = "marks",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "List of marks",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Mark.class)
                                            )
                                    ),
                                    headers = {
                                            @Header(
                                                    name = "X-Total-Count",
                                                    description = "Number of marks returned."
                                            )
                                    }
                            )
                    })
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(marksBean.getAll(queryParams))
                .header("X-Total-Count", marksBean.getAllCount(queryParams))
                .build();
    }

    @Operation
            (description = "Returns mark specified by id.", summary = "Specified mark",
                    tags = "marks",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Specified mark",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Mark.class)
                                            )
                                    )
                            )
                    })
    @GET
    @Path("{id}")
    public Response getMark(@PathParam("id") Integer id) {
        Mark mark = marksBean.get(id);
        if (mark != null) {
            return Response.ok(mark).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @Operation
            (description = "Creates a new mark with specified attributes.", summary = "New mark",
                    tags = "marks",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Created mark",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Mark.class)
                                            )
                                    )
                            )
                    })
    @POST
    public Response addMark(Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.add(mark))
                .build();
    }

    /@Operation
            (description = "Updates a mark with specified id.", summary = "Update mark",
                    tags = "marks",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Updated mark",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Mark.class)
                                            )
                                    )
                            )
                    })
    @PUT
    @Path("{id}")
    public Response updateMark(@PathParam("id") Integer id, Mark mark) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.update(id, mark))
                .build();
    }

    @Operation
            (description = "Deletes a mark with specified id.", summary = "Delete mark",
                    tags = "marks",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Deleted mark",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Marks.class)
                                            )
                                    )
                            )
                    })
    @DELETE
    @Path("{id}")
    public Response deleteMark(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(marksBean.remove(id))
                .build();
    }
}