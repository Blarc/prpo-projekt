package v1.apis;

import beans.ItemsBean;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import managers.ItemSearchManager;
import managers.RecommendationsManager;

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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, PUT, DELETE, OPTIONS")
public class ItemsAPI {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ItemsBean itemsBean;

    @Inject
    private ItemSearchManager itemSearchManager;

    @Inject
    private RecommendationsManager recommendationsManager;


    @Operation
            (description = "Returns list of items.", summary = "List of items",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "List of items",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    ),
                                    headers = {
                                            @Header(
                                                    name = "X-Total-Count",
                                                    description = "Number of items returned."
                                            )
                                    }
                            )
                    })
    @GET
    public Response getAll() {
        QueryParameters queryParams = QueryParameters.query(this.uriInfo.getRequestUri().getQuery()).build();
        return Response
                .ok(this.itemsBean.getAll(queryParams))
                .header("X-Total-Count", this.itemsBean.getAllCount(queryParams))
                .build();
    }


    @Operation
            (description = "Returns recommended items.", summary = "Recommended items",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Recommended items",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })
    @GET
    @Path("/recommendations")
    public Response getRecommended() {
        return Response
                .ok(this.recommendationsManager.getRecommendations())
                .build();
    }

    @Operation
            (description = "Returns an item specified by id.", summary = "Specified item",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Specified item",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })

    @GET
    @Path("{id}")
    public Response getItem(@PathParam("id") Integer id) {
        Item item = this.itemsBean.get(id);
        if (item != null) {
            return Response.ok(item).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Operation
            (description = "Creates a new item with specified attributes.", summary = "New item",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Created item",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })
    @POST
    public Response addItem(Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(this.itemsBean.add(item))
                .build();
    }

    @Operation
            (description = "Updates an item with specified id.", summary = "Update item",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Updated item",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })
    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") Integer id, Item item) {
        return Response
                .status(Response.Status.OK)
                .entity(this.itemsBean.update(id, item))
                .build();
    }

    @Operation
            (description = "Deletes an item with specified id.", summary = "Delete item",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Deleted item",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })
    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(this.itemsBean.remove(id))
                .build();
    }

    @Operation
            (description = "Gets item by name similarity to given string", summary = "Item by name",
                    tags = "items",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Item by name",
                                    content = @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Item.class)
                                            )
                                    )
                            )
                    })
    @GET
    @Path("/name/{name}")
    public Response getItemByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(itemSearchManager.getByName(name))
                .build();
    }
}
