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
        service.step1(clientId);
        service.step2(clientId);
    }


    @Override
    @Step
    public void step1(String tenantId) {
        ClientDataService dataService = Arc.container().instance(ClientDataService.class).get();
        var requestContext = Arc.container().requestContext();
        requestContext.activate(); // required for quarkus hibernate multi tenancy resolver
        try {
            TenantContext.set(tenantId);
            List<RefClient> clients = dataService.getClients();
            dataService.logClients(clients);
        } finally {
            TenantContext.clear();
            requestContext.terminate();
        }
    }

    @Override
    @Step
    public void step2(String tenantId) {
        ClientDataService dataService = Arc.container().instance(ClientDataService.class).get();

        var requestContext = Arc.container().requestContext();
        requestContext.activate();
        try {
            TenantContext.set(tenantId);
            List<RefClientAddress> clientAddress = dataService.getClientAddress();
            dataService.logClientAddress(clientAddress);
        } finally {
            TenantContext.clear();
            requestContext.terminate();
        }

    }
}