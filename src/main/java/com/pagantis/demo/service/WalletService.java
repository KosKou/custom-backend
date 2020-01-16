package com.pagantis.demo.service;

import com.pagantis.demo.entity.User;
import com.pagantis.demo.entity.Wallet;
import com.pagantis.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserService userService;

    public List<Wallet> findAll(){
        return walletRepository.findAll();
    }

    public Optional<Wallet> findById(String id){
        return walletRepository.findById(id);
    }

    public List<Wallet> findAllByUser(String email){
        return walletRepository.findAllByUserMail(email);
    }

    public boolean isWalletExist(String id){
        return walletRepository.existsById(id);
    }

    //Update & Save
    public Wallet createWallet(Wallet wallet){
        //Validates Users
        Optional<User> user = userService.findByEmail(wallet.getUserMail());
        if (user.isPresent()){
            return walletRepository.save(wallet);
        }else {
            return null;
        }
    }
    public Wallet updateWallet(Wallet wallet){
        return walletRepository.save(wallet);
    }

    public void deleteWallet(String id){
        walletRepository.deleteById(id);
    }
    public void deleteWalletsByUser(String email){
        walletRepository.deleteAllByUserMail(email);
    }
}
