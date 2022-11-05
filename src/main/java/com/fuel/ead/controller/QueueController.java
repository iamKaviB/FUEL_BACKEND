package com.fuel.ead.controller;

import com.fuel.ead.dto.QueueCount;
import com.fuel.ead.dto.QueueDto;
import com.fuel.ead.model.Queue;
import com.fuel.ead.model.Shed;
import com.fuel.ead.service.QueueService;
import com.fuel.ead.service.TankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/queue")
public class QueueController {

    private QueueService queueService;
    private TankService tankService;

    @PostMapping("/create")
    public ResponseEntity<Queue> create(@RequestBody QueueDto dto){
        HttpStatus response = HttpStatus.BAD_REQUEST;
        Queue responsed=new Queue();
        String msg;

        if(dto ==null){
            msg="queue should not be null";
        }else if(dto.getTank() == null || dto.getTank().equals("")){
            msg="tank name required";
        }else if(dto.getCustomer() == null || dto.getCustomer().equals("")){
            msg="customer required";
        }else{
            Queue queue = new Queue();
            queue.setTank(tankService.findById(dto.getTank()));
            queue.setCustomer(dto.getCustomer());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            queue.setTime(dtf.format(now));
            queue.setStatus(dto.getStatus());
            responsed = queueService.create(queue);
            response= HttpStatus.ACCEPTED;
            msg="created "+responsed.toString();
        }

        return new ResponseEntity<>(responsed,response);

    }

    @PutMapping("/update")
    public ResponseEntity<Queue> update(@RequestBody QueueDto dto){
        HttpStatus response = HttpStatus.BAD_REQUEST;
        Queue retrieved = new Queue();
        String msg;

        if(dto ==null){
            msg="queue should not be null";
        }else if(dto.getId() == null || dto.getId().equals("")){
            msg="queue required";
        }else if(dto.getStatus() == null || dto.getStatus().equals("")){
            msg="status required";
        }else{
            retrieved = queueService.findById(dto.getId());
            retrieved.setStatus(dto.getStatus());
            queueService.create(retrieved);
            response= HttpStatus.CREATED;
        }

        return new ResponseEntity<>(retrieved,response);
    }

    @PostMapping("/count")
    public ResponseEntity<QueueCount> count(@RequestBody QueueDto dto){
        HttpStatus response = HttpStatus.BAD_REQUEST;
        QueueCount count = new QueueCount();
        List<Queue> retrieved = new ArrayList<>();

        if(dto.getId() == null || dto.getId().equals("")){

        }else if(dto.getStatus() == null || dto.getStatus().equals("")){

        }else{
            retrieved = queueService.findTime(dto.getTank(),dto.getStatus());
            count.setCount(retrieved.size());
            count.setStatus(dto.getStatus());
            count.setTime(retrieved.get(0).getTime());
            response= HttpStatus.OK;

        }

        return new ResponseEntity<>(count,response);


    }


}
