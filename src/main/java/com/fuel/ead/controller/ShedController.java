package com.fuel.ead.controller;

import com.fuel.ead.dto.ShedDto;
import com.fuel.ead.model.Customer;
import com.fuel.ead.model.Shed;
import com.fuel.ead.service.ShedMasterService;
import com.fuel.ead.service.ShedService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/shed")
public class ShedController {

    private ShedService shedService;
    private ShedMasterService shedMasterService;

    @PostMapping("/create")
    public ResponseEntity<Shed> create(@RequestBody ShedDto dto){
        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg;
        Shed responsed = new Shed();

        if(dto ==null){
            throw  new RuntimeException("shed incorrect");
        }else if(dto.getName() == null || dto.getName().equals("")){
            throw  new RuntimeException("name incorrect");
        }else if(dto.getLocation() == null || dto.getLocation().equals("")){
            throw  new RuntimeException("location incorrect");
        }else{
            Shed shed = new Shed();
            shed.setName(dto.getName());
            shed.setLocation(dto.getLocation());
            shed.setShedMaster(shedMasterService.findById(dto.getShedMaster()));
            responsed = shedService.create(shed);
            response= HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<>(responsed,response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Shed>> findAll(){
        return new ResponseEntity<>(shedService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findAllByMaster")
    public ResponseEntity<List<Shed>> findAllByShedMaster(@PathVariable(name = "id") String id){
        return new ResponseEntity<>(shedService.findAllByShedMaster(id),HttpStatus.OK);
    }
}
