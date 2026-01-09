package com.example.dbos.quarkus.poc.workflow;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/workflow")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class WorkflowResource {

    @Inject
    WorkflowInterface workflowService;


    @POST
    @Path("/invoke")
    public String invokeWorkflow(@HeaderParam("X-Tenant-ID") String clientCode) {
        try {
            Log.info("Invoking workflow for client: " + clientCode);
            workflowService.invokeWorkflow(clientCode);
            return "success";
        } catch (Exception e) {
            Log.error("Error in workflow ", e);
            return "Error in workflow " + e.getMessage();
        }
    }

}