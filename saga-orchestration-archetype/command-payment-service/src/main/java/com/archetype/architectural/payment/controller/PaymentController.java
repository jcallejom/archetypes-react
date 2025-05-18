package com.archetype.architectural.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.PaymentCommandRequest;
import com.archetype.architectural.dto.PaymentCommandResponse;
import com.archetype.architectural.payment.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/debit")
    public PaymentCommandResponse debit(@RequestBody PaymentCommandRequest requestDTO){
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentCommandRequest requestDTO){
        this.service.credit(requestDTO);
    }

}
