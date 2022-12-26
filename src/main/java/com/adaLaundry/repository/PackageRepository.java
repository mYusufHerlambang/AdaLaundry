package com.adaLaundry.repository;

import com.adaLaundry.entity.Packages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackageRepository extends JpaRepository<Packages, Long> {


    @Query("""
            SELECT pc
            FROM Packages pc
            """)
    Page<Packages> findAllPackage(Pageable pageable);

    @Query("""
            SELECT pc
            FROM Packages pc
            WHERE pc.packageName = :packageName
            """)
    Packages findByName(String packageName);
}
