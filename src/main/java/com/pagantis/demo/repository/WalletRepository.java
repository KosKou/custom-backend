package com.pagantis.demo.repository;

import com.pagantis.demo.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {
    List<Wallet> findAllByUserMail(String email);
    void deleteAllByUserMail(String email);
}

