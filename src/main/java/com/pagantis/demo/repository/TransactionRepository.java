package com.pagantis.demo.repository;

import com.pagantis.demo.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByFrom(String fromId);
    List<Transaction> findAllByTo(String toId);
}

