package com.dyma.tennis.service;

import com.dyma.tennis.ApplicationStatus;
import com.dyma.tennis.HealthCheck;
import com.dyma.tennis.data.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    @Autowired
    private HealthCheckRepository healthCheckRepository;

    public HealthCheck healthCheck(){
        Long activeSessions = healthCheckRepository.countApplicationConnections();
        if(activeSessions > 0){
            return new HealthCheck(ApplicationStatus.OK, "Welcome on Dyma Tennis!");
        } else {
            return new HealthCheck(ApplicationStatus.KO, "Dyma Tennis is not fully functional, please check your configuration.");
        }
    }
}
