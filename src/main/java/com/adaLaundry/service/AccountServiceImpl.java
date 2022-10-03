package com.adaLaundry.service;

import com.adaLaundry.ApplicationUserDetails;
import com.adaLaundry.entity.Account;
import com.adaLaundry.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(username);

        Account tempAccount = null;

        if (accountOptional.isPresent()){
            tempAccount = accountOptional.get();
        }

        return new ApplicationUserDetails(tempAccount);
    }
}
