package com.wanfeng.dingyuehao.component.command;

import com.wanfeng.dingyuehao.domain.VO.MsgReq;
import com.wanfeng.dingyuehao.domain.VO.WeatherForecast;
import com.wanfeng.dingyuehao.domain.VO.WeatherLive;
import com.wanfeng.dingyuehao.domain.VO.WeatherResp;
import com.wanfeng.dingyuehao.service.WeatherService;
import com.wanfeng.dingyuehao.domain.template.StringTemplate;
import com.wanfeng.dingyuehao.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Component("天气")
public class WeatherHandler implements CommandHandler{

    @Autowired
    private WeatherService weatherService;

    @Override
    public String handler(Matcher matcher, MsgReq msgReq) {
        WeatherResp weather = weatherService.getLiveWeather("和平区");
        WeatherLive live = weather.getLives().get(0);
        WeatherResp forecastWeather = weatherService.getForecastWeather("和平区");
        WeatherForecast weatherForecast = forecastWeather.getForecasts().get(0);
        String collect = weatherForecast.getCasts().stream().map(i->{
            String week = i.getDate();
            String dayweather = i.getDayweather();
            String daytemp = i.getDaytemp();
            return week+":"+dayweather+","+daytemp+"℃";
        }).collect(Collectors.joining("\n"));
        String reply = String.format(StringTemplate.WeatherTemplate,"和平区",live.getWeather(),live.getTemperature()+"℃",live.getWindpower(),"\n"+collect+"\n");
        return MsgUtils.buildReply(msgReq,reply);
    }

    @Override
    public String desc() {
        return "查询当前天气以及天气预报";
    }
}
