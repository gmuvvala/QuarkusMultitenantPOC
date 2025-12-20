package com.example.dbos.quarkus.poc;


import com.example.dbos.quarkus.poc.workflow.WorkflowInterface;
import com.example.dbos.quarkus.poc.workflow.WorkflowService;
import dev.dbos.transact.DBOS;
import dev.dbos.transact.config.DBOSConfig;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.jboss.logging.Logger;

@ApplicationScoped
public class DbosConfig {

    @Inject
    WorkflowInterface workflowService;
    private static final Logger LOG = Logger.getLogger(DbosConfig.class);


    void onStartup(@Observes StartupEvent ev) {
        LOG.info("Setting DBOS config on  startup.");
        DBOSConfig config = DBOSConfig.defaults("multiPoc")
                .withDatabaseUrl("jdbc:postgresql://localhost:5432/dbos")
                .withDbUser("postgres")
                .withDbPassword("dbos");
        DBOS.configure(config);

        workflowService.toString();
        // Your application runs here
        System.out.println("DBOS started on startup.");
    }


    @Produces
    @ApplicationScoped
    public WorkflowInterface getWorkflowService() {
        LOG.info("Creating WorkflowService");
        WorkflowInterface impl = new WorkflowService();
        var proxy = DBOS.registerWorkflows(WorkflowInterface.class, impl);
        LOG.info("Created WorkflowService proxy");
        impl.setProxy(proxy);
        return proxy;
    }
}
