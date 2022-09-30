package com.adaLaundry.service;

import com.adaLaundry.dto.packages.PackageUpsertDTO;
import com.adaLaundry.entity.Packages;
import com.adaLaundry.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService{

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public Page<Packages> getAllPackage(Pageable pageable) {

        Page<Packages> packagesGrid = packageRepository.findAllPackage(pageable);

        return packagesGrid;
    }

    @Override
    public Packages getPackageById(Long id) {

        Optional<Packages> packagesOptional = packageRepository.findById(id);

        Packages packageById = null;

        if(packagesOptional.isPresent()){
            packageById = packagesOptional.get();

        }

        return packageById;
    }

    @Override
    public void deleteById(Long id) {

        packageRepository.deleteById(id);
    }

    @Override
    public Packages insertNewPackage(PackageUpsertDTO insertDTO) {

        Packages newPackage = new Packages(
                insertDTO.getPackageName(),
                insertDTO.getPrice()
        );

        System.out.println("ini new package "+newPackage);

        packageRepository.save(newPackage);

        return newPackage;
    }

    @Override
    public Packages updatePackageById(PackageUpsertDTO updateDto, Long id) {

        Packages packageById = getPackageById(id);

        if (packageById != null){
            packageById.setPackageName(updateDto.getPackageName());
            packageById.setPrice(updateDto.getPrice());
        }

        packageRepository.save(packageById);
        return packageById;
    }
}
