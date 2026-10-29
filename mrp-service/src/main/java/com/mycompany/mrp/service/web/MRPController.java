package com.mycompany.mrp.service.web;

import com.mycompany.mrp.service.dto.MRPRequest;
import com.mycompany.mrp.service.dto.MRPResult;
import com.mycompany.mrp.service.logic.MRPService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mrp")
public class MRPController {

    private final MRPService mrpService;

    public MRPController(MRPService mrpService) {
        this.mrpService = mrpService;
    }

    @PostMapping
    public MRPResult calculateMRP(@RequestBody MRPRequest request) {
        return mrpService.calculateMRP(request);
    }

    @GetMapping("/health")
    public String health() {
        return "MRP Service is running";
    }
}