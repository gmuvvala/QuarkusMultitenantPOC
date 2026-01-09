package com.example.dbos.quarkus.poc;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@PersistenceUnitExtension
public class HibernateTenantResolver implements TenantResolver {


    @Override
    public String getDefaultTenantId() {
        return "c1";
    }

    @Override
    public String resolveTenantId() {
        //Log.info("TenantContext.get() = " + TenantContext.get());
        String tenantId = TenantContext.get();
        if (tenantId != null && (tenantId.equals("c1") || tenantId.equals("c2"))) {
            return tenantId;
        }
        return getDefaultTenantId();
    }
}