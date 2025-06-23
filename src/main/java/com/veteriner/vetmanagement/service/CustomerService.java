package com.veteriner.vetmanagement.service;

import com.veteriner.vetmanagement.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomer(Long id);
    List<Customer> getAllCustomers();
    List<Customer> searchByName(String name);
}
