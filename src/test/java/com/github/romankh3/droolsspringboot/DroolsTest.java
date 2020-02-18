package com.github.romankh3.droolsspringboot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration-level testing for Drools rules.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsTest {

    @Autowired
    private KieSession kieSession;

    @Test
    public void shouldFireRuleWithVisa() {
        // given
        Order order = new Order();
        order.setCardType("VISA");
        order.setPrice(14000);
        kieSession.insert(order);

        // when
        kieSession.fireAllRules();

        // then
        assertEquals(java.util.Optional.of(14).get(), order.getDiscount());
    }

    @Test
    public void shouldFireRuleWithMasterCard() {
        // given
        Order order = new Order();
        order.setCardType("MASTERCARD");
        order.setPrice(14000);
        kieSession.insert(order);

        // when
        kieSession.fireAllRules();

        // then
        assertEquals(java.util.Optional.of(10).get(), order.getDiscount());
    }

    @Test
    public void shouldFireRuleWithICICI() {
        // given
        Order order = new Order();
        order.setCardType("ICICI");
        order.setPrice(3001);
        kieSession.insert(order);

        // when
        kieSession.fireAllRules();

        // then
        assertEquals(java.util.Optional.of(20).get(), order.getDiscount());
    }
}
