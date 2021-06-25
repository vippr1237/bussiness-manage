package com.fresher_devops.simple_bussiness.services;

import com.fresher_devops.simple_bussiness.exception.ReceiptDetailNotFoundException;
import com.fresher_devops.simple_bussiness.models.ReceiptDetail;
import com.fresher_devops.simple_bussiness.repositories.ReceiptDetailRepo;
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
class ReceiptDetailServiceTest {

    @Mock
    private ReceiptDetailRepo receiptDetailRepo;
    private ReceiptDetailService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ReceiptDetailService(receiptDetailRepo);
    }

    @Test
    void canAddReceiptDetail() {
        ReceiptDetail receiptDetail = new ReceiptDetail(null, null, 1);
        //when
        underTest.addReceiptDetail(receiptDetail);
        //then
        ArgumentCaptor<ReceiptDetail> receiptDetailArgumentCaptor = ArgumentCaptor.forClass(ReceiptDetail.class);
        verify(receiptDetailRepo).save(receiptDetailArgumentCaptor.capture());
        ReceiptDetail capturedReceiptDetail = receiptDetailArgumentCaptor.getValue();
        assertThat(capturedReceiptDetail).isEqualTo(receiptDetail);
    }

    @Test
    void canFindAllReceiptDetails() {
        //when
        underTest.findAllReceiptDetails();
        //then
        verify(receiptDetailRepo).findAll();
    }

    @Test
    void canUpdateReceiptDetail() {
        ReceiptDetail receiptDetail = new ReceiptDetail(null, null, 1);
        //when
        underTest.updateReceiptDetail(receiptDetail);
        //then
        ArgumentCaptor<ReceiptDetail> receiptDetailArgumentCaptor = ArgumentCaptor.forClass(ReceiptDetail.class);
        verify(receiptDetailRepo).save(receiptDetailArgumentCaptor.capture());
        ReceiptDetail capturedReceiptDetail = receiptDetailArgumentCaptor.getValue();
        assertThat(capturedReceiptDetail).isEqualTo(receiptDetail);
    }

    @Test
    void canFindReceiptDetailById() {
        Long id = 1L;
        //when
        ReceiptDetail receiptDetail = new ReceiptDetail(null, null, 1);
        when(receiptDetailRepo.findReceiptDetailById(id)).thenReturn(Optional.of(receiptDetail));
        underTest.findReceiptDetailById(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(receiptDetailRepo).findReceiptDetailById(idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }

    @Test
    void willThrowIfIdDoesNotExits() {
        Long id = 1L;
        //then
        assertThatThrownBy(() -> underTest.findReceiptDetailById(id))
                .isInstanceOf(ReceiptDetailNotFoundException.class)
                .hasMessage("ReceiptDetail by id " + id + " was not found");
    }

    @Test
    void canDeleteReceiptDetail() {
        Long id = 1L;
        //when
        underTest.deleteReceiptDetail(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(receiptDetailRepo).deleteReceiptDetailById(
                idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }
}