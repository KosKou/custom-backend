package com.pagantis.demo.controller;

import com.pagantis.demo.entity.Wallet;
import com.pagantis.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/api/fillWallets")
    public String initWallets(){
        walletService.deleteWalletsByUser("user1@gmail.com");
        walletService.deleteWalletsByUser("user2@gmail.com");
        walletService.createWallet(new Wallet("user1@gmail.com", (long) 1500));
        walletService.createWallet(new Wallet("user1@gmail.com", (long) 3000));
        walletService.createWallet(new Wallet("user1@gmail.com", (long) 300));
        walletService.createWallet(new Wallet("user2@gmail.com", (long) 500));
        return "The wallets have been initialized";
    }
    @GetMapping("/api/wallets")
    public List<Wallet> findAll(){
        return walletService.findAll();
    }

    @GetMapping("/api/wallets/{email}")
    public List<Wallet> listByUser(@PathVariable String email){
        return walletService.findAllByUser(email);
    }


}
