package com.planck.onlineshop;
import com.planck.DAO.CustomerDAO;
import com.planck.Model.Customer;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerDAO customerDAO = new CustomerDAO();

    @RequestMapping("/list")
    public ArrayList<Customer> listCustomers(){
        return customerDAO.listCustomers();
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        customerDAO.createCustomer(customer);
        return customer;
    }

    // Read = GET
    @GetMapping(value = "{customerIdCard}")
    public Customer getCustomerById(@PathVariable("customerIdCard") long customerIdCard) {
        return customerDAO.searchCustomer(CustomerDAO.SQL_CUSTOMER_ID_CARD, customerIdCard+"");
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerDAO.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping(value = "{customerIdCard}")
    public void deleteCustomer(@PathVariable("customerIdCard") long customerIdCard) {
        customerDAO.deleteCustomer(customerIdCard);
    }
}