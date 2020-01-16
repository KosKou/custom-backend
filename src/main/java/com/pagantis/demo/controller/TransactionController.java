package com.pagantis.demo.controller;

import com.pagantis.demo.entity.Transaction;
import com.pagantis.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/api/transactions")
    public List<Transaction> retrieveAll(){
        return transactionService.findAll();
    }

    @GetMapping("/api/transactions/{id}")
    public Transaction retrieveTransactionById(@PathVariable String id){
        return transactionService.findById(id).get();
    }

    @PostMapping("/api/transactions")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction){
            Transaction create = transactionService.createTransaction(transaction);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(create.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }
}
