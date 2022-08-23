package com.wanfeng.dingyuehao.controller;

import com.wanfeng.dingyuehao.domain.VO.MsgReq;
import com.wanfeng.dingyuehao.domain.VO.WxBaseVo;
import com.wanfeng.dingyuehao.service.MsgSendService;
import com.wanfeng.dingyuehao.service.TagService;
import com.wanfeng.dingyuehao.service.WeatherService;
import com.wanfeng.dingyuehao.service.WxBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/wx")
public class WxBaseController {

    @Autowired
    private WxBaseService wxBaseService;
    @Autowired
    private MsgSendService msgSendService;
    @Autowired
    private TagService tagService;

    @GetMapping("/validate")
    public String validate(WxBaseVo wxBaseVo){
        log.info("微信后台绑定服务区测试->[{}]",wxBaseService.check(wxBaseVo));
        return wxBaseVo.getEchostr();
    }

    @PostMapping(value = "/validate", produces = {"application/xml"})
    public Object getMsg(@RequestBody MsgReq msgFromUser) {
        return wxBaseService.handle(msgFromUser);
    }

    @GetMapping("/sendAll")
    public void SendAll() {
        msgSendService.sendMsgToAllJustText(msgSendService.buildText());
    }


    @PostMapping("/addTag/{name}")
    public void addTag(@PathVariable String name){
        tagService.create(name);
    }

    @Autowired
    private WeatherService weatherService;
    @GetMapping("/test")
    public void test(){
        weatherService.getForecastWeather("霍州市");
    }
}
