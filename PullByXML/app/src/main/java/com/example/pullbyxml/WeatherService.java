package com.example.pullbyxml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WeatherService {

    //使用xml解析的方式
    public static List<WeatherInfo> getInfosFromXML(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<WeatherInfo> weatherInfos = null;
        WeatherInfo weatherInfo = null;

        int type = parser.getEventType();

        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        weatherInfos = new ArrayList<>();
                    } else if("city".equals(parser.getName())){
                        weatherInfo = new WeatherInfo();
                        String  idStr = parser.getAttributeValue(0);
                        weatherInfo.setId(idStr);
                    } else if("temp".equals(parser.getName())){
                        String temp = parser.nextText();
                        weatherInfo.setTemp(temp);
                    } else if ("weather".equals(parser.getName())) {
                        String weather = parser.nextText();
                        weatherInfo.setWeather(weather);
                    } else if ("name".equals(parser.getName())) {
                        String name = parser.nextText();
                        weatherInfo.setName(name);
                    } else if ("pm".equals(parser.getName())) {
                        String pm = parser.nextText();
                        weatherInfo.setPm(pm);
                    } else if ("wind".equals(parser.getName())) {
                        String wind = parser.nextText();
                        weatherInfo.setWind(wind);
                    }
                    break;


                case XmlPullParser.END_TAG:
                    if ("city".equals(parser.getName())) {
                        weatherInfos.add(weatherInfo);
                        weatherInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return weatherInfos;
    }

    //使用Google的Gson解析
    public static List<WeatherInfo> getInfoFromJson(InputStream is) throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);

        String json = new String(buffer, "utf-8");
        Gson gson = new Gson();

        Type listType = new TypeToken<List<WeatherInfo>>(){}.getType();
        List<WeatherInfo> weatherInfos = gson.fromJson(json, listType);
        return weatherInfos;
    }
}