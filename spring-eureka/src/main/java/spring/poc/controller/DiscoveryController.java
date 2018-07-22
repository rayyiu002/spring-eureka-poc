package spring.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by rayyiu on 22/7/2018.
 */

@RestController
public class DiscoveryController {

    private final Logger logger = Logger.getLogger("DiscoveryController");

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/instances", method = RequestMethod.GET)
    public String getInstances() {

        if (client != null) {

            client.getServices().forEach(id -> {
                client.getInstances(id).forEach(instance -> {
                    logger.info("/instance, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
                });
            });

        } else {
            logger.info("No instance discovered");
            return "FAIL";
        }

        return "SUCCESS";
    }

}
