package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.Customer;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.exception.ConflictException;
import com.veteriner.vetmanagement.repository.CustomerRepository;
import com.veteriner.vetmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        boolean exists = customerRepository.findByNameContainingIgnoreCase(customer.getName()).stream()
                .anyMatch(c -> c.getMail().equalsIgnoreCase(customer.getMail()));
        if (exists) {
            throw new ConflictException("Kayıt sistemde mevcut.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = getCustomer(id);
        existing.setName(customer.getName());
        existing.setPhone(customer.getPhone());
        existing.setMail(customer.getMail());
        existing.setAddress(customer.getAddress());
        existing.setCity(customer.getCity());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existing = getCustomer(id);
        customerRepository.delete(existing);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li müşteri bulunamadı."));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}
