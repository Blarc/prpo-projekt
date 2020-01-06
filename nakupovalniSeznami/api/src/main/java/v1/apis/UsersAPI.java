package v1.apis;

import beans.UsersBean;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, PUT, DELETE, OPTIONS")
public class UsersAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UsersBean usersBean;

    @Operation
            (description = "Returns list of users.", summary = "List of users",
                    tags = "users",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "List of users",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class)
                                            )
                                    ),
                                    headers = {
                                            @Header(
                                                    name = "X-Total-Count",
                                                    description = "Number of users returned."
                                            )
                                    }
                            )
                    })
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(usersBean.getAll(queryParams))
                .header("X-Total-Count", usersBean.getAllCount(queryParams))
                .build();
    }

    @Operation
            (description = "Returns user specified by id.", summary = "Specified user",
                    tags = "users",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Specified user",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class)
                                            )
                                    )
                            )
                    })
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id) {
        User user = usersBean.get(id);

        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Operation
            (description = "Creates a new user with specified attributes.", summary = "New user",
                    tags = "users",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Created user",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class)
                                            )
                                    )
                            )
                    })
    @POST
    public Response addUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.add(user))
                .build();
    }


    @Operation
            (description = "Updates a user with specified id.", summary = "Update user",
                    tags = "users",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Updated user",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class)
                                            )
                                    )
                            )
                    })
    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.update(id, user))
                .build();
    }

    @Operation
            (description = "Deletes a user with specified id.", summary = "Delete user",
                    tags = "users",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Deleted user",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class)
                                            )
                                    )
                            )
                    })
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(usersBean.remove(id))
                .build();
    }
}
