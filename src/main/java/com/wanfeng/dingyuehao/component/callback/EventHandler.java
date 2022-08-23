package com.wanfeng.dingyuehao.component.callback;


import com.wanfeng.dingyuehao.domain.VO.MsgReq;

public interface EventHandler {
    public void handle(MsgReq msgReq);
}
