package com.example.dbos.quarkus.poc.repositories;

import com.example.dbos.quarkus.poc.TenantContext;
import com.example.dbos.quarkus.poc.domain.RefClient;
import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Unremovable
public class RefClientRepository {

    @Transactional
    public List<RefClient> findAllByTenant(String tenantId) {
        Log.info("Finding clients for tenant: " + tenantId);
        try {
            TenantContext.set(tenantId);
            return RefClient.listAll();
        } finally {
            TenantContext.clear();
        }
    }
}