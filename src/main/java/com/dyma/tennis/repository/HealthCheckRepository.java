package com.dyma.tennis.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class HealthCheckRepository {

    @Autowired
    private EntityManager entityManager;

    @Value("${spring.datasource.username}")
    private String mariadbUser;

    public Long countApplicationConnections(){
        String applicationConnectionsQuery = String.format("select count(*) from information_schema.PROCESSLIST  where user = '%s'", mariadbUser);
        return (Long) entityManager.createNativeQuery(applicationConnectionsQuery).getSingleResult();
    }
}
