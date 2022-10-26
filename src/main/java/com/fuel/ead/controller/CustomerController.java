package com.fuel.ead.controller;

import com.fuel.ead.dto.PasswordResetDto;
import com.fuel.ead.model.Customer;
import com.fuel.ead.service.CustomerService;
import com.fuel.ead.service.PasswordHashing;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg;

            if(customer ==null){
                msg="customer should not be null";
            }else if(customer.getEmail() == null || customer.getEmail().equals("")){
                msg="email incorrect";
            }else if(customer.getPassword() == null || customer.getEmail().equals("")){
                msg="password incorrect";
            }else{
                customer.setPassword(PasswordHashing.encrypt(customer.getPassword()));
                customerService.register(customer);
                response =HttpStatus.CREATED;
                msg="created "+customer.toString();
            }

            return new ResponseEntity<>(msg, response);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        Customer responsed = new Customer();

        if(customer ==null){
            throw  new RuntimeException("customer should not be null");
        }else if(customer.getEmail() == null || customer.getEmail().equals("")){
            throw  new RuntimeException("email required");
        }else if(customer.getPassword() == null || customer.getPassword().equals("")){
            throw  new RuntimeException("password required");
        }else{
            responsed = customerService.login(customer);
            response= HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<>(responsed, response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathVariable(name = "id") String id){
        HttpStatus response = HttpStatus.OK;
        String msg="success";
        try{
            customerService.delete(id);
        }catch (Exception e){
            response=HttpStatus.EXPECTATION_FAILED;
            msg="failed";
        }
        return new ResponseEntity<>(msg,response);
    }

    @PutMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody PasswordResetDto customer){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg="failed";
        if(customer ==null){
            msg="customer should not be null";
        }else if(customer.getId() == null || customer.getId().equals("")){
            msg="id incorrect";
        }else if(customer.getCurrentPassword() == null || customer.getCurrentPassword().equals("")){
            msg="current password required";
        }else if(customer.getNewPassword() == null || customer.getNewPassword().equals("")){
            msg="new password required";
        }else{

            Customer responed = customerService.findById(customer.getId());
            if(PasswordHashing.decrypt(responed.getPassword()).equals(customer.getCurrentPassword())){
                responed.setPassword(PasswordHashing.encrypt(customer.getCurrentPassword()));
                customerService.register(responed);
                msg="success";
                response = HttpStatus.ACCEPTED;
            }

        }

        return new ResponseEntity<>(msg, response);
    }

}
