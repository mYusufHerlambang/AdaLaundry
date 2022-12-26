package com.adaLaundry.service;

import com.adaLaundry.dto.account.AdminInsertDTO;
import com.adaLaundry.dto.account.AdminUpdateDTO;
import com.adaLaundry.entity.Account;
import com.adaLaundry.entity.Packages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    String getAccountRole(String username);

    Page<Account> getAllAdmin(Pageable pageable);

    Account insertNewAdmin(AdminInsertDTO insertDTO);

    Account getAdminByUsername(String username);

    void deleteByUsername(String username);

    Account updateAdmin(AdminUpdateDTO updateDTO, String username);

    boolean checkExistingUsername(String username);
}
