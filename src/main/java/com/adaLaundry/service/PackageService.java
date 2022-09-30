package com.adaLaundry.service;

import com.adaLaundry.dto.packages.PackageUpsertDTO;
import com.adaLaundry.entity.Packages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PackageService {
    Page<Packages> getAllPackage(Pageable pageable);

    Packages getPackageById(Long id);

    void deleteById(Long id);

    Packages insertNewPackage(PackageUpsertDTO packageUpsertDTO);

    Packages updatePackageById(PackageUpsertDTO updateDto, Long id);
}
