package br.com.organomeno.ofx.rest;

import br.com.organomeno.ofx.services.OfxService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.MultipartForm;
import java.io.FileInputStream;



@Path("/ofx")
public class OfxRest {

    @Inject
    OfxService ofxService;

    private static final Logger LOG = Logger.getLogger(OfxRest.class.getName());


    @GET
    @Path("/")
    public String hello() {
        return "Hello RESTEasy Reactive";
    }

    @POST
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response upload(@MultipartForm MulitipleDocumentDetailsRequest documentDetailsRequests) {

        documentDetailsRequests.getFileUpload().forEach(fileUpload -> {
            LOG.info("File name: " + fileUpload.name());

            String arquivo = fileUpload.uploadedFile().toString();
            try {
                FileInputStream streamFile = new FileInputStream(arquivo);
                ofxService.fazerLeituraDeOFX(documentDetailsRequests);
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }

            LOG.info("getUsuario: " + documentDetailsRequests.getUsuario());
        });

        return Response.ok(documentDetailsRequests).build();
    }



}
