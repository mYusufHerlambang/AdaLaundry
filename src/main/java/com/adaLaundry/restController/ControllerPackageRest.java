package com.adaLaundry.restController;

import com.adaLaundry.dto.packages.PackageUpsertDTO;
import com.adaLaundry.entity.Packages;
import com.adaLaundry.restExceptionHandler.NotFoundException;
import com.adaLaundry.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/package")
public class ControllerPackageRest {

    @Autowired
    private PackageService packageService;

    private final Integer maxRows = 10;

    @GetMapping("/index")
    public ResponseEntity<Page<Packages>> packageIndex(@RequestParam(defaultValue = "1") Integer page){

        Pageable pageable = PageRequest.of(page - 1, maxRows, Sort.by("id"));

        Page<Packages> packagesGrid = packageService.getAllPackage(pageable);

        return new ResponseEntity<>(packagesGrid, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePackage(@RequestParam(required = true) Long id){
        Packages packageById = packageService.getPackageById(id);

        if(packageById == null){
            throw new NotFoundException("Package with Id " + id + " Not Found!");
        } else{
            packageService.deleteById(id);
            return new ResponseEntity<>("Success Delete Package With Name " + packageById.getPackageName(), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> insertPackage(@Valid @RequestBody PackageUpsertDTO insertDTO){

        Packages packages = packageService.insertNewPackage(insertDTO);

        return new ResponseEntity<>(packages, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Packages> updatePackage(@RequestBody PackageUpsertDTO updateDto,
                                                   @RequestParam(required = true)Long id){

        Packages packageById = packageService.getPackageById(id);

        if (packageById != null){
            Packages packageUpdate = packageService.updatePackageById( updateDto, id);

            return new ResponseEntity<>(packageUpdate, HttpStatus.ACCEPTED);
        }else{

            throw new NotFoundException("Package with Id " + id + " Not Found!");
        }
    }
}
