package com.example.dbos.quarkus.poc.repositories;

import com.example.dbos.quarkus.poc.domain.RefClient;
import io.quarkus.arc.Unremovable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Unremovable
public class RefClientRepository {

    @Transactional
    public List<RefClient> findAll() {
        return RefClient.listAll();

    }
}