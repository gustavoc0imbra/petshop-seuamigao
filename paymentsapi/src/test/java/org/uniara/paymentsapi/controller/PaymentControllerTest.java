package org.uniara.paymentsapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.uniara.paymentsapi.constant.Constant;
import org.uniara.paymentsapi.model.Payment;
import org.uniara.paymentsapi.service.PaymentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@ActiveProfiles("test")
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve retornar lista com todos pagamentos")
    void it_should_return_list_all_payments() throws Exception {
        List<Payment> payments = setupPayments();

        when(paymentService.findAll()).thenReturn(payments);

        mockMvc.perform(MockMvcRequestBuilders.get(Constant.API_PAYMENTS_URL)
                        .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andDo(result -> assertEquals(toJson(payments), result.getResponse().getContentAsString()));

        verify(paymentService).findAll();
    }

    @Test
    @DisplayName("Deve retornar um pagamento pelo Id")
    void it_should_return_payment_by_id() throws Exception {
        Payment payment = setupPayment();

        when(paymentService.findById(payment.getId())).thenReturn(Optional.of(payment));

        mockMvc.perform(MockMvcRequestBuilders.get(Constant.API_PAYMENTS_URL + "/" + payment.getId())
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andDo(result -> assertEquals(toJson(payment), result.getResponse().getContentAsString()));

        verify(paymentService).findById(payment.getId());
    }

    @Test
    @DisplayName("Deve retornar vazio quando não encontrar pagamento pelo Id")
    void it_should_return_empty_when_payment_by_id_not_found() throws Exception {
        Payment payment = setupPayment();

        when(paymentService.findById(payment.getId())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(Constant.API_PAYMENTS_URL + "/" + payment.getId())
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("null"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));

        verify(paymentService).findById(payment.getId());
    }

    @Test
    @DisplayName("Deve retornar pagamento salvo")
    void it_should_return_saved_payment() throws Exception {
        Payment payment = setupPayment();
        payment.setId(null);

        Payment expectedPayment = setupPayment();

        when(paymentService.save(payment)).thenReturn(expectedPayment);

        String paymentJson = toJson(payment);

        mockMvc.perform(MockMvcRequestBuilders.post(Constant.API_PAYMENTS_URL)
                            .header("Accept", "application/json")
                            .content(paymentJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(payment.getId()))
                .andExpect(jsonPath("$.orderId").value(payment.getOrderId()))
                .andExpect(jsonPath("$.status").value(payment.getStatus()))
                .andExpect(jsonPath("$.paymentMethod").value(payment.getPaymentMethod()))
                .andExpect(jsonPath("$.totalAmount").value(payment.getTotalAmount()))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));

        verify(paymentService).save(payment);
    }

    @Test
    @DisplayName("Deve retornar pagamento atualizado")
    void it_should_return_updated_payment() throws Exception {
        Payment payment = setupPayment();

        payment.setStatus('F');

        when(paymentService.save(payment)).thenReturn(payment);

        mockMvc.perform(MockMvcRequestBuilders.put(Constant.API_PAYMENTS_URL)
                            .header("Accept", "application/json")
                            .content(toJson(payment))
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(payment.getId()))
                .andExpect(jsonPath("$.orderId").value(payment.getOrderId()))
                .andExpect(jsonPath("$.status").value(payment.getStatus()))
                .andExpect(jsonPath("$.paymentMethod").value(payment.getPaymentMethod()))
                .andExpect(jsonPath("$.totalAmount").value(payment.getTotalAmount()))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));

        verify(paymentService).save(payment);
    }

    @Test
    @DisplayName("Deve retornar no-content ao deletar pagamento")
    void it_should_return_no_content_when_delete_payment() throws Exception {
        Payment payment = setupPayment();

        mockMvc.perform(MockMvcRequestBuilders.delete(Constant.API_PAYMENTS_URL + "/" + payment.getId())
                .header("Accept", "application/json"))
                .andExpect(status().isNoContent());

        verify(paymentService).deleteById(payment.getId());
    }

    private String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Payment> setupPayments() {


        List<Payment> payments = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Payment payment = new Payment();
            payment.setId(Long.parseLong("" + i));
            payment.setOrderId(Long.parseLong("" + i));
            payment.setStatus('P');
            payment.setTotalAmount(new BigDecimal(150));
            payment.setPaymentMethod("PIX");

            payments.add(payment);
        }

        return payments;
    }

    private Payment setupPayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId(1L);
        payment.setStatus('P');
        payment.setTotalAmount(new BigDecimal(150));
        payment.setPaymentMethod("PIX");

        return payment;
    }
}