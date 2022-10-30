package com.fuel.ead.controller;

import com.fuel.ead.dto.TankDto;
import com.fuel.ead.model.Tank;
import com.fuel.ead.service.ShedService;
import com.fuel.ead.service.TankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/tank")
public class TankController {

    private TankService tankService;
    private ShedService shedService;

    @PostMapping("/create")
    public ResponseEntity<Tank> create(@RequestBody TankDto tankDto){

        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg;
        Tank responsed =new Tank();

        if(tankDto ==null){
            throw  new RuntimeException("tank incorrect");
        }else if(tankDto.getFuelType() == null || tankDto.getFuelType().equals("")){
            throw  new RuntimeException("fuel type incorrect");
        }else if(tankDto.getShed() == null || tankDto.getShed().equals("")){
            throw  new RuntimeException("shed incorrect");
        }else{
            Tank tank = new Tank();
            tank.setFuelType(tankDto.getFuelType());
            tank.setStatus(tankDto.isStatus());
            tank.setShed(shedService.findById(tankDto.getShed()));
            responsed = tankService.create(tank);
            response =HttpStatus.CREATED;
            msg="created "+responsed.toString();
        }

        return new ResponseEntity<>(responsed, response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Tank>> findAll(){
        return new ResponseEntity<>(tankService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findAllByShed/{id}")
    public ResponseEntity<List<Tank>> findAllByShed(@PathVariable(name = "id") String shed){
        return new ResponseEntity<>(tankService.findAllByShedId(shed),HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<String> updateStauts(@RequestBody TankDto tankDto){
        HttpStatus response = HttpStatus.BAD_REQUEST;
        String msg;

        if(tankDto.getTankId()==null || tankDto.getTankId().equals("")){
            msg="tank required";
        }else{
            response=HttpStatus.CREATED;
            Tank tank = tankService.findById(tankDto.getTankId());
            tank.setStatus(tankDto.isStatus());
            Tank responsed = tankService.create(tank);
            msg="updated successfully "+responsed.toString();
        }

        return new ResponseEntity<>(msg,response);

    }

}
