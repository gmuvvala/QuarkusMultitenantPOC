package com.example.dbos.quarkus.poc;

import dev.dbos.transact.DBOS;
import io.quarkus.arc.Arc;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.Optional;

@QuarkusMain
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String... args) {
        Quarkus.run(MultiTenantApp.class, args);
    }

    public static class MultiTenantApp implements QuarkusApplication {

        @ConfigProperty(name = "quarkus.profile")
        String profile;

        @ConfigProperty(name = "servicingApp.mode")
        Optional<String> appModeOptl;


        @Override
        public int run(String... args) throws Exception {


            // Launch DBOS
            DBOS.launch();


            LOG.info(String.format("Running Servicing app in API mode using profile %s", profile));
            Quarkus.waitForExit();

            return 0;
        }
    }

}