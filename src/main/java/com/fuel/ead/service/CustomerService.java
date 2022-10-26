package com.fuel.ead.service;

import com.fuel.ead.model.Customer;
import com.fuel.ead.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer register(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(String id){
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("customer doesnt exist"));
    }

    public Customer login(Customer customer){
        Customer reponsed = customerRepository.findByEmail(customer.getEmail());
        if(PasswordHashing.decrypt(reponsed.getPassword()).equals(customer.getPassword())){
            return reponsed;
        }else {
            throw new RuntimeException("bad credentials");
        }

    }

    public void delete(String id){
        customerRepository.deleteById(id);
    }
}
