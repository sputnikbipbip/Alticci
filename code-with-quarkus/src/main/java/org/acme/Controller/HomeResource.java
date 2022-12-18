package org.acme.Controller;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;



@Path("home")
public class HomeResource {
    
    @Inject
    Template home;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance homepage() {
        return home.data("intro", "Hello, wellcome.");
    }
}
