package cu.dev.halal.service.impl;

import cu.dev.halal.service.AddressService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class AddressServiceImpl implements AddressService {

    private final static Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);


    // naver api 사용  주소 -> 좌표 변환
    @Override
    public LinkedHashMap toCoordinate(String address) {
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
        url = url + "?query="+address;
        LinkedHashMap returnValue = new LinkedHashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", "rnl7q9733x");
        httpHeaders.add("X-NCP-APIGW-API-KEY", "iiK2zg20DDqp6yoVsdnryVn7DG4SkYz7ehaxL1aO");

        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, JSONObject.class);
        if(response.getStatusCode() == HttpStatus.OK){
            JSONObject jsonObject = (JSONObject) response.getBody();
            ArrayList addresses = (ArrayList) jsonObject.get("addresses");
            try{
                LinkedHashMap coordinate = (LinkedHashMap) addresses.get(0);
                return coordinate;
            }catch (IndexOutOfBoundsException e){
                returnValue.put("result", "invalid address");
                return returnValue;
            }
        }
        else {

            returnValue.put("result", "naver api error");
            return returnValue;
        }

    }

    // 좌표간 거리 계산
    public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    @Override
    public Double dis(Double x1, Double y1, Double x2, Double y2){
        Double x1d = Math.floor(x1);
        Double x1m = Math.floor((x1 - x1d)*60);
        Double x1s = Double.valueOf(Math.floor(((x1 - x1d)*60-x1m)*60*100)/100);
        Double earth = 6378.135;

        Double y1d = Math.floor(y1);
        Double y1m = Math.floor((y1 - y1d)*60);
        Double y1s = Double.valueOf(Math.floor(((y1 - y1d)*60-y1m)*60*100)/100);

        Double x2d = Math.floor(x2);
        Double x2m = Math.floor((x2 - x2d)*60);
        Double x2s = Double.valueOf(Math.floor(((x2 - x2d)*60-x2m)*60*100)/100);

        Double y2d = Math.floor(y2);
        Double y2m = Math.floor((y2 - y2d)*60);
        Double y2s = Double.valueOf(Math.floor(((y2 - y2d)*60-y2m)*60*100)/100);


        Double result = Math.sqrt(Math.pow((x1d-x2d)*88.9036+(x1m-x2m)*1.4817+(x1s-x2s)*0.0246,2)
                + Math.pow((y1d-y2d)*111.3194+(y1m-y2m)*1.8553+(y1s-y2s)*0.0309,2));
        Double C = Math.cos(x1d)*(2*Math.PI*earth/360);


        // km 단위
        return result;
    }

}
