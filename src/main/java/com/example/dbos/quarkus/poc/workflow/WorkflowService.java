package com.example.dbos.quarkus.poc.workflow;


import com.example.dbos.quarkus.poc.TenantContext;
import com.example.dbos.quarkus.poc.domain.RefClient;
import com.example.dbos.quarkus.poc.domain.RefClientAddress;
import com.example.dbos.quarkus.poc.repositories.ClientDataService;
import dev.dbos.transact.workflow.Workflow;
import dev.dbos.transact.workflow.Step;
import io.quarkus.arc.Arc;
import io.quarkus.arc.Unremovable;

import java.util.List;

@Unremovable
public class WorkflowService implements WorkflowInterface {

    WorkflowInterface service;


    @Override
    public void setProxy(WorkflowInterface proxy) {
        this.service = proxy;
    }

    @Workflow
    public void invokeWorkflow(String clientId) {
        TenantContext.set(clientId);
        service.step1();
        service.step2();
    }

    @Override
    @Step
    public void step1() {
        ClientDataService dataService = Arc.container().instance(ClientDataService.class).get();
        String tenantId = TenantContext.get() != null ? TenantContext.get() : "c1";
        List<RefClient> clients = dataService.getClients(tenantId);
        dataService.logClients(clients);

    }

    @Override
    @Step
    public void step2() {
        ClientDataService dataService = Arc.container().instance(ClientDataService.class).get();
        String tenantId = TenantContext.get() != null ? TenantContext.get() : "c1";
        List<RefClientAddress> clientAddress = dataService.getClientAddress(tenantId);
        dataService.logClientAddress(clientAddress);

    }
}