package com.pagantis.demo.service;

import com.pagantis.demo.entity.Transaction;
import com.pagantis.demo.entity.Wallet;
import com.pagantis.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletService walletService;
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    public List<Transaction> findAllByFrom(String id){
        return transactionRepository.findAllByFrom(id);
    }
    public List<Transaction> findAllByTo(String id){
        return transactionRepository.findAllByTo(id);
    }

    public Optional<Transaction> findById(String id){
        return transactionRepository.findById(id);
    }

    public boolean isWalletExist(String id){
        return transactionRepository.existsById(id);
    }

    //Update & Save
    public Transaction createTransaction(Transaction transaction){
        //From & To referes to wallets id's
        transaction.setState("INPROG");
        //Validations
        Wallet walletFrom = walletService.findById(transaction.getFrom()).get();
        Wallet walletTo = walletService.findById(transaction.getTo()).get();
        //Check From Balance
        if (walletFrom.getBalance() > 0){
            if (walletFrom.getBalance() >= transaction.getAmount()){
                //Math Operation
                walletFrom.setBalance(walletFrom.getBalance() - transaction.getAmount());
                walletTo.setBalance(walletTo.getBalance() + transaction.getAmount());
                //Save new Balances
                walletService.updateWallet(walletFrom);
                walletService.updateWallet(walletTo);
                //Set transaction history values
                transaction.setState("SUCCESSFUL");
                transaction.setMessage("The operation has completed.");
            }else {
                transaction.setState("FAILED");
                transaction.setMessage("The Wallet From has not enough money.");
            }
        }else{
            transaction.setState("FAILED");
            transaction.setMessage("The Wallet From has no money.");
        }
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(String id){
        transactionRepository.deleteById(id);
    }
}
