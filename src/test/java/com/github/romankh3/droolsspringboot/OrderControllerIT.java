package com.github.romankh3.droolsspringboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Integration-level testing for {@link OrderController} object.
 * Show the result of the Drools engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    public static final String VISA = "VISA";
    public static final String MASTER_CARD = "MASTERCARD";

    @Test
    public void shouldApplyVISARule() throws Exception {
        //given
        Order order = new Order();
        order.setCardType(VISA);
        order.setPrice(11000);

        //when
        MockHttpServletRequestBuilder request = post("/order")
                .content(objectMapper.writeValueAsBytes(order))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        String contentAsString = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Order resultOrder = objectMapper.readValue(contentAsString, Order.class);

        //then
        Assert.assertEquals(order.getCardType(), resultOrder.getCardType());
        Assert.assertEquals(order.getPrice(), resultOrder.getPrice());
        Assert.assertEquals(java.util.Optional.of(14).get(), resultOrder.getDiscount());
    }

    @Test
    public void shouldApplyMASTERCARDRuleRule() throws Exception {
        //given
        Order order = new Order();
        order.setCardType(MASTER_CARD);
        order.setPrice(11000);

        //when
        MockHttpServletRequestBuilder request = post("/order")
                .content(objectMapper.writeValueAsBytes(order))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        String contentAsString = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Order resultOrder = objectMapper.readValue(contentAsString, Order.class);

        //then
        Assert.assertEquals(order.getCardType(), resultOrder.getCardType());
        Assert.assertEquals(order.getPrice(), resultOrder.getPrice());
        Assert.assertEquals(java.util.Optional.of(10).get(), resultOrder.getDiscount());
    }

}
