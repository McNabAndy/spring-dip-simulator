package cz.dipcom.simulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {

    @GetMapping("/api/ping")
    public String simulate() {
        return "pong!";
    }
}
