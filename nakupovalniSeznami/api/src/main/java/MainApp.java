import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@OpenAPIDefinition(
        info = @Info(
                title = "Rest API",
                version = "v1",
                contact = @Contact(),
                license = @License(),
                description = "Java API for managing e-shopping."
        ),
        security = @SecurityRequirement(
                name = "openid-connect"
        ),
        servers = @Server(
                url = "http://localhost:8080/v1",
                description = "Public api"
        )
)
@ApplicationPath("v1")
public class MainApp extends Application {

}
