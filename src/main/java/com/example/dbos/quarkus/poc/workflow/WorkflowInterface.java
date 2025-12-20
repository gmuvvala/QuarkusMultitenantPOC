package com.example.dbos.quarkus.poc.workflow;


import dev.dbos.transact.workflow.Workflow;
import jakarta.transaction.Transactional;

public interface WorkflowInterface {

    void setProxy(WorkflowInterface proxy);

    @Workflow
    void invokeWorkflow(String clientId);

    @Transactional
    void step1();

    @Transactional
    void step2();
}