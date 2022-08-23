package com.wanfeng.dingyuehao;

import com.wanfeng.dingyuehao.domain.Enum.PaymentEnum;
import com.wanfeng.dingyuehao.domain.VO.PaymentReq;
import com.wanfeng.dingyuehao.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = DingyuehaoApplication.class)
class DingyuehaoApplicationTests {

    @Autowired
    private PaymentService paymentService;

    @Test
    void contextLoads() {
        PaymentReq paymentReq = new PaymentReq();
        paymentReq.setAmount(BigDecimal.valueOf(100.24));
        paymentReq.setType(PaymentEnum.Pay.getType());
        paymentReq.setUserid("test");
        paymentService.addPayment(paymentReq);
    }

}
