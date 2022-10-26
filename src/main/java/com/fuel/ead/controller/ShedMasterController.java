package com.fuel.ead.controller;

import com.fuel.ead.dto.PasswordResetDto;
import com.fuel.ead.model.Customer;
import com.fuel.ead.model.ShedMaster;
import com.fuel.ead.service.PasswordHashing;
import com.fuel.ead.service.ShedMasterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/shedmaster")
public class ShedMasterController {

    private ShedMasterService shedMasterService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ShedMaster shedMaster){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg;

            response = HttpStatus.BAD_REQUEST;
            if(shedMaster ==null){
                msg="user should not be null";
            }else if(shedMaster.getEmail() == null || shedMaster.getEmail().equals("")){
                msg="username incorrect";
            }else if(shedMaster.getPassword() == null || shedMaster.getPassword().equals("")){
                msg="password incorrect";
            }else{
                shedMaster.setPassword(PasswordHashing.encrypt(shedMaster.getPassword()));
                shedMasterService.register(shedMaster);
                response =HttpStatus.CREATED;
                msg="created "+shedMaster.toString();
            }

            return new ResponseEntity<>(msg, response);
    }

    @PostMapping("/login")
    public ResponseEntity<ShedMaster> login(@RequestBody ShedMaster shedMaster){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        ShedMaster responsed = new ShedMaster();

        if(shedMaster ==null){
            throw  new RuntimeException("user should not be null");
        }else if(shedMaster.getEmail() == null || shedMaster.getEmail().equals("")){
            throw  new RuntimeException("email incorrect");
        }else if(shedMaster.getPassword() == null || shedMaster.getPassword().equals("")){
            throw  new RuntimeException("password should not be null");
        }else{
            responsed = shedMasterService.login(shedMaster);
            response =HttpStatus.CREATED;
        }

        return new ResponseEntity<>(responsed, response);
    }

    @PutMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody PasswordResetDto dto){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg="failed";
        if(dto ==null){
            msg="master should not be null";
        }else if(dto.getId() == null || dto.getId().equals("")){
            msg="id incorrect";
        }else if(dto.getCurrentPassword() == null || dto.getCurrentPassword().equals("")){
            msg="current password required";
        }else if(dto.getNewPassword() == null || dto.getNewPassword().equals("")){
            msg="new password required";
        }else{

            ShedMaster responed = shedMasterService.findById(dto.getId());
            if(PasswordHashing.decrypt(responed.getPassword()).equals(dto.getCurrentPassword())){
                responed.setPassword(PasswordHashing.encrypt(dto.getCurrentPassword()));
                shedMasterService.register(responed);
                msg="success";
                response = HttpStatus.ACCEPTED;
            }

        }

        return new ResponseEntity<>(msg, response);
    }

}
