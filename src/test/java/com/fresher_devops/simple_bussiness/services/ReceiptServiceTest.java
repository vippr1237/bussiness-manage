package com.fresher_devops.simple_bussiness.services;

import com.fresher_devops.simple_bussiness.exception.ReceiptNotFoundException;
import com.fresher_devops.simple_bussiness.models.Receipt;
import com.fresher_devops.simple_bussiness.repositories.ReceiptRepo;
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
class ReceiptServiceTest {

    @Mock
    private ReceiptRepo receiptRepo;
    private ReceiptService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ReceiptService(receiptRepo);
    }

    @Test
    void canAddReceipt() {
        Receipt receipt = new Receipt(null, 1, null);
        //when
        underTest.addReceipt(receipt);
        //then
        ArgumentCaptor<Receipt> receiptArgumentCaptor = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepo).save(receiptArgumentCaptor.capture());
        Receipt capturedReceipt = receiptArgumentCaptor.getValue();
        assertThat(capturedReceipt).isEqualTo(receipt);
    }

    @Test
    void canFindAllReceipts() {
        //when
        underTest.findAllReceipts();
        //then
        verify(receiptRepo).findAll();
    }

    @Test
    void canUpdateReceipt() {
        Receipt receipt = new Receipt(null, 1, null);
        //when
        underTest.updateReceipt(receipt);
        //then
        ArgumentCaptor<Receipt> receiptArgumentCaptor = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepo).save(receiptArgumentCaptor.capture());
        Receipt capturedReceipt = receiptArgumentCaptor.getValue();
        assertThat(capturedReceipt).isEqualTo(receipt);
    }

    @Test
    void canFindReceiptById() {
        Long id = 1L;
        //when
        Receipt receipt = new Receipt(null, 1, null);
        when(receiptRepo.findReceiptById(id)).thenReturn(Optional.of(receipt));
        underTest.findReceiptById(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(receiptRepo).findReceiptById(idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }

    @Test
    void willThrowIfIdDoesNotExits() {
        Long id = 1L;
        //then
        assertThatThrownBy(() -> underTest.findReceiptById(id))
                .isInstanceOf(ReceiptNotFoundException.class)
                .hasMessage("Receipt by id " + id + " was not found");
    }

    @Test
    void canDeleteReceipt() {
        Long id = 1L;
        //when
        underTest.deleteReceipt(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(receiptRepo).deleteReceiptById(
                idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }
}