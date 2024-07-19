package com.openclassrooms.medilabofront.client.medilaboResult;

import com.openclassrooms.medilabofront.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8090/api", name = "MedilaboResult", configuration = FeignConfiguration.class)
public interface MedilaboResultClient {

    @GetMapping("/result/{patientId}")
    String getMedilaboResult(@PathVariable("patientId") String patientId);
}
