package com.adaLaundry.service;

import com.adaLaundry.ApplicationUserDetails;
import com.adaLaundry.config.RestSecurityConfig;
import com.adaLaundry.dto.account.AdminInsertDTO;
import com.adaLaundry.dto.account.AdminUpdateDTO;
import com.adaLaundry.entity.Account;
import com.adaLaundry.entity.Packages;
import com.adaLaundry.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Override
    public String getAccountRole(String username) {
        Optional<Account> findAccount = accountRepository.findById(username);

        Account tempAccount = null;
        String role = null;

        if (findAccount.isPresent()){
            tempAccount = findAccount.get();

            role = tempAccount.getRole();
        }
        return role;
    }

    @Override
    public Page<Account> getAllAdmin(Pageable pageable) {

        Page<Account> admins = accountRepository.findAllAdmin(pageable);
        return admins;
    }

    @Override
    public Account insertNewAdmin(AdminInsertDTO insertDTO) {

        PasswordEncoder passwordEncoder = RestSecurityConfig.passwordEncoder();

        String hashPassword = passwordEncoder.encode(insertDTO.getPassword());

        Account newAdmin = new Account(
                insertDTO.getUsername(),
                hashPassword,
                "Admin"
        );

        accountRepository.save(newAdmin);

        return newAdmin;
    }

    @Override
    public Account getAdminByUsername(String username) {

        Optional<Account> accountOptional = accountRepository.findById(username);

        Account account = null;

        if (accountOptional.isPresent()){
            account = accountOptional.get();
        }

        return account;
    }

    @Override
    public void deleteByUsername(String username) {
        accountRepository.deleteById(username);
    }

    @Override
    public Account updateAdmin(AdminUpdateDTO updateDTO, String username) {

        PasswordEncoder passwordEncoder = RestSecurityConfig.passwordEncoder();

        String hashPassword = passwordEncoder.encode(updateDTO.getPassword());

        Account account = getAdminByUsername(username);

        if (account != null ){
            account.setPassword(hashPassword);
        }

        accountRepository.save(account);
        return account;
    }

    @Override
    public boolean checkExistingUsername(String username) {
        Long totalUsername = accountRepository.count(username);

        return totalUsername > 0 ? true : false;
    }
}
