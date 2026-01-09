package com.example.dbos.quarkus.poc.repositories;

import com.example.dbos.quarkus.poc.domain.RefClientAddress;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Unremovable
public class RefClientAddressRepository {

    @Transactional
    public List<RefClientAddress> findAll() {

        return RefClientAddress.listAll();

    }
}