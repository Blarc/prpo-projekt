package v1.mappers;

import exceptions.IllegalShoppingListDtoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalShoppingListDtoExceptionMapper implements ExceptionMapper<IllegalShoppingListDtoException> {

    @Override
    public Response toResponse(IllegalShoppingListDtoException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}")
                .build();
    }
}
