package com.example.dbos.quarkus.poc.repositories;

import com.example.dbos.quarkus.poc.domain.RefClient;
import com.example.dbos.quarkus.poc.domain.RefClientAddress;
import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Unremovable
public class ClientDataService {
    @Inject
    RefClientRepository refClientRepository;

    @Inject
    RefClientAddressRepository refClientAddressRepository;

    @Transactional
    public List<RefClient> getClients(String tenantId) {
        Log.info("Getting clients for tenant: " + tenantId);
        return refClientRepository.findAllByTenant(tenantId);
    }

    @Transactional
    public List<RefClientAddress> getClientAddress(String tenantId) {
        Log.info("Getting client Address  for tenant: " + tenantId);
        return refClientAddressRepository.findAllByTenant(tenantId);
    }


    public void logClients(List<RefClient> clients) {
        clients.forEach(client ->
                Log.info("Client details :" + client.getClientName()));
    }

    public void logClientAddress(List<RefClientAddress> clientAddressList) {
        clientAddressList.forEach(address ->
                Log.info("Client Address : " +
                        String.join(" ",
                                address.getClientCode(),
                                address.getAddressLine1(),
                                address.getAddressLine2())));

    }
}