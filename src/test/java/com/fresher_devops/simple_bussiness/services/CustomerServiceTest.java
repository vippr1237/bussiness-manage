package com.fresher_devops.simple_bussiness.services;

import com.fresher_devops.simple_bussiness.exception.CustomerNotFoundException;
import com.fresher_devops.simple_bussiness.models.Customer;
import com.fresher_devops.simple_bussiness.repositories.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepo);
    }

    @Test
    void canAddCustomer() {
        //give
        Customer customer = new Customer("A", "B", 20, "12312312");
        underTest.addCustomer(customer);
        //test
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepo).save(customerArgumentCaptor.capture());
        assertThat(customerArgumentCaptor.getValue()).isEqualTo(customer);
    }

    @Test
    void canFindAllCustomers() {
        //when
        underTest.findAllCustomers();
        //then
        verify(customerRepo).findAll();
    }

    @Test
    void canUpdateCustomer() {
        Customer customer = new Customer("A", "B", 20, "1234");
        //when
        underTest.updateCustomer(customer);
        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepo).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer).isEqualTo(customer);
    }

    @Test
    void canFindCustomerById() {
        Long id = 1L;
        //when
        Customer customer = new Customer("A", "B", 20, "1234");
        when(customerRepo.findCustomerById(id)).thenReturn(Optional.of(customer));
        underTest.findCustomerById(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(customerRepo).findCustomerById(idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }

    @Test
    void willThrowIfIdDoesNotExits() {
        Long id = 1L;
        //then
        assertThatThrownBy(() -> underTest.findCustomerById(id))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer by id " + id + " was not found");
    }

    @Test
    void canDeleteCustomer() {
        Long id = 1L;
        //when
        underTest.deleteCustomer(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(customerRepo).deleteCustomerById(
                idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }
}