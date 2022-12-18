package org.acme.Controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.Service.AlticciCacheService;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("alticci")
public class AlticciResource {

    private AlticciCacheService alticciCacheService = new AlticciCacheService();
    @Inject
    Template showResult;

    @Path("/{n}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance calculate(@PathParam("n") int n) {
        if(n < 0) {
            return showResult.data("n", n,"result", "Error\n\nPath parameter must be an value > 0");
        }
        return showResult.data("n", n, "result", alticciCacheService.calculate(n));
    }


}
