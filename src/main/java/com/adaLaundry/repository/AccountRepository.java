package com.adaLaundry.repository;

import com.adaLaundry.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("""
            SELECT acc.username
            FROM Account acc
            WHERE acc.role = 'Admin'
            """)
    Page<Account> findAllAdmin(Pageable pageable);

    @Query("""
            SELECT COUNT(*)
            From Account AS acc
            WHERE acc.username = :username
            """)
    Long count(String username);
}
