package com.wanfeng.dingyuehao.component.command;

import com.wanfeng.dingyuehao.domain.Enum.PaymentEnum;
import com.wanfeng.dingyuehao.domain.VO.MsgReq;
import com.wanfeng.dingyuehao.domain.VO.PaymentReq;

import com.wanfeng.dingyuehao.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.regex.Matcher;

import static com.wanfeng.dingyuehao.component.command.SalaryHandler.insertAndReturnRecord;

@Component("消费")
public class PaymentHandler implements CommandHandler{

    @Autowired
    private PaymentService paymentService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String handler(Matcher matcher, MsgReq msgReq) {
        return insertAndReturnRecord(matcher, msgReq, paymentService);
    }

    @Override
    public String desc() {
        return "添加一条消费记录";
    }
}