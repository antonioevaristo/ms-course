package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PaymentService {

    //TODO usando restTemplate
//    @Value("${hr-worker.host}")
//    private String workerHost;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, Integer days){
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id",workerId.toString());

      //  Worker worker = restTemplate.getForObject(workerHost + "/worker/{id}", Worker.class, uriVariables);

        Worker worker = workerFeignClient.findById(workerId).getBody();

        log.info("Sucesso na comunicação com hr-worker, " + worker);

        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days).build();
    }
}
