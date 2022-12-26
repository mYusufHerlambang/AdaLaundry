package com.adaLaundry.restController;

import com.adaLaundry.dto.account.AdminInsertDTO;
import com.adaLaundry.dto.account.AdminUpdateDTO;
import com.adaLaundry.dto.account.RequestTokenDTO;
import com.adaLaundry.dto.account.ResponseTokenDTO;
import com.adaLaundry.entity.Account;
import com.adaLaundry.restExceptionHandler.InternalServerError;
import com.adaLaundry.restExceptionHandler.InvalidUsernameOrPassword;
import com.adaLaundry.restExceptionHandler.NotFoundException;
import com.adaLaundry.service.AccountService;
import com.adaLaundry.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class ControllerAccountRest {
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Integer maxRows = 10;


    @PostMapping("/login")
    public ResponseTokenDTO login(@Valid @RequestBody RequestTokenDTO requestTokenDTO){

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            requestTokenDTO.getUsername(),
                            requestTokenDTO.getPassword()
                    );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            String role = accountService.getAccountRole(requestTokenDTO.getUsername());

            String token = jwtToken.generateToken(
                    requestTokenDTO.getUsername(),
                    role
            );

            ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO(
                    requestTokenDTO.getUsername(),
                    role,
                    token
            );

            return responseTokenDTO;

        }catch (Exception e){
            throw new InvalidUsernameOrPassword("Invalid Username or Password");
        }
    }

    @GetMapping("/index")
    public ResponseEntity<Object> adminIndex(@RequestParam(defaultValue = "1") Integer page){

        try {
            Pageable pageable = PageRequest.of(page - 1, maxRows, Sort.by("id"));

            Page<Account> grid = accountService.getAllAdmin(pageable);

            return new ResponseEntity<>(grid, HttpStatus.OK);

        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }


    }

    @PostMapping("/add")
    public ResponseEntity<Object> insertAdmin(@Valid @RequestBody AdminInsertDTO insertDTO){

        try {
            Account account = accountService.insertNewAdmin(insertDTO);

            return new ResponseEntity<>(account, HttpStatus.CREATED);

        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAdmin(@RequestParam(required = true) String username){

        try {
            Account account = accountService.getAdminByUsername(username);

            if(account == null){
                throw new NotFoundException("Admin with Username " + username + " Not Found!");
            } else{
                accountService.deleteByUsername(username);

                return new ResponseEntity<>("Success Delete Admin With Name " + username, HttpStatus.OK);
            }
        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAccountByOwnSelf(@Valid @RequestBody AdminUpdateDTO updateDTO,
                                                        HttpServletRequest request){
        try {
            String username = request.getUserPrincipal().getName();

            Account admin = accountService.getAdminByUsername(username);

            if (admin != null){
                Account updateAdmin = accountService.updateAdmin( updateDTO, username);

                return new ResponseEntity<>("Success update Password", HttpStatus.ACCEPTED);
            }else{
                throw new NotFoundException("Admin with Name " + username+ " Not Found!");
            }

        }catch (Exception e){

            throw new InternalServerError("There is a run-time error on the server.");
        }
    }
}
