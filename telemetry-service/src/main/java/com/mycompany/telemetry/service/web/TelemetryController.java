/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.telemetry.service.web;

import com.mycompany.telemetry.service.dto.Telemetry;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    @PostMapping
    public Telemetry collectTelemetry(@RequestBody Telemetry telemetry) {
        return telemetry;
    }

    @GetMapping("/health")
    public String health() {
        return "Telemetry Service is running";
    }
}
