package v1.apis;

import beans.ShoppingListsBean;
import com.kumuluz.ee.rest.beans.QueryParameters;
import dtos.ShoppingListDto;
import entities.ShoppingList;
import exceptions.IllegalShoppingListDtoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation
            (description = "Returns list of shoppingLists.", summary = "List of shoppingLists",
                    tags = "shoppingLists",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "List of shoppingLists",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ShoppingList.class)
                                            )
                                    ),
                                    headers = {
                                            @Header(
                                                    name = "X-Total-Count",
                                                    description = "Number of shoppingLists returned."
                                            )
                                    }
                            )
                    })
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(shoppingListsBean.getAll(queryParams))
                .header("X-Total-Count", shoppingListsBean.getAllCount(queryParams))
                .build();
    }

    @Operation
            (description = "Returns shoppingList specified by id.", summary = "Specified shoppingList",
                    tags = "shoppingLists",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Specified shoppingList",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ShoppingList.class)
                                            )
                                    )
                            )
                    })
    @GET
    @Path("{id}")
    public Response getShoppingList(@PathParam("id") Integer id) {
        ShoppingList shoppingList = shoppingListsBean.get(id);
        if (shoppingList == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(shoppingList).build();
    }

     //sj tukej ni treba DTO ane?
    @Operation
            (description = "Creates a new shoppingList with specified attributes.", summary = "New shoppingList",
                    tags = "shoppingLists",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Created shoppingList",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ShoppingList.class)
                                            )
                                    )
                            )
                    })
    @POST
    public Response addShoppingList(ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = sm.createShoppingList(shoppingListDto);
        if (shoppingList == null) {
            throw new IllegalShoppingListDtoException("Invalid parameters!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.add(shoppingList))
                .build();

    }

    @Operation
            (description = "Updates a shoppingList with specified id.", summary = "Update shoppingList",
                    tags = "shoppingLists",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Updated shoppingList",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ShoppingList.class)
                                            )
                                    )
                            )
                    })
    @PUT
    @Path("{id}")
    public Response updateShoppingList(@PathParam("id") Integer id, ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = sm.updateShoppingList(id, shoppingListDto);
        if (shoppingList == null) {
            throw new IllegalShoppingListDtoException("Invalid parameters!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.update(id, shoppingList))
                .build();
    }

    @Operation
            (description = "Deletes a shoppingList with specified id.", summary = "Delete shoppingList",
                    tags = "shoppingLists",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Deleted shoppingList",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ShoppingList.class)
                                            )
                                    )
                            )
                    })
    @DELETE
    @Path("{id}")
    public Response deleteShoppingList(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(shoppingListsBean.remove(id))
                .build();
    }
}