package com.wanfeng.dingyuehao.component.command;

import com.wanfeng.dingyuehao.domain.VO.MsgReq;

import java.util.regex.Matcher;

public interface CommandHandler {
    public String handler(Matcher matcher, MsgReq msgReq);

    public String desc();
}
