package com.koreait.spring2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class ApartPriceService {
    @Autowired
    private ApartPriceMapper mapper;



    public List<LocationCodeEntity> selLocationCodeList() {
        return mapper.selLocationCodeList(null);
    }

    public void saveData(SearchDTO param) {
        List<ResultEntity> dbList = mapper.selApartmentInfoList(param);

        if(dbList.size() > 0) { // 중복제거(DB에 있다면 intrn_area_code값이 1이상이기떄문에)
            return;
        }

        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
//        final String serviceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
        String decodeServiceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";

//        try {
//            decodeServiceKey = URLDecoder.decode(serviceKey,"UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        final HttpHeaders HEADERS = new HttpHeaders();
//        // 기본 JSON으로 넘어온다. APPLICATION_XML로 해주면 XML로 받음
//        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        final HttpEntity<String> ENTITY = new HttpEntity<String>(HEADERS);

        String deal_ym = String.format("%s%02d", param.getDeal_year(), param.getDeal_month());

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD",param.getExtrn_area_code())
                .queryParam("DEAL_YMD",deal_ym)
                .queryParam("serviceKey",decodeServiceKey)
                .queryParam("numOfRows","1000") // numOfRows는 기본값이 존재( 10 )하고, 필수아닌 옵션임
                .build(false);

        String urlWithParam = url + "?LAWD_CD=" + param.getExtrn_area_code() + "&DEAL_YMD=" + deal_ym + "&serviceKey=" + decodeServiceKey;
        RestTemplate rest = new RestTemplate();

        rest.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
        // 응답을 String으로 받겠다는 뜻

        String result =  responseEntity.getBody();
        System.out.println("result : " + result);

        // JAVA 객체로 변환 하기전에 구조를 다지는 구간
        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 알수없는(내가 받고싶지 않은)property는 허용하지 않음
        JsonNode jsonNode = null;
        ResultEntity[] list = null;
        try {
            jsonNode = om.readTree(result);
            list = om.treeToValue(jsonNode.path("response")
                    .path("body")
                    .path("items")
                    .path("item"), ResultEntity[].class); // 객체화
            //response아래, body아래, itmes아래의 item에 접근을 한 뒤에 배열로 받는다??
            System.out.println("list.length : " + list.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(ResultEntity item : list) {
            System.out.println("itetet : "+item);
        }

        List<LocationCodeEntity> locationList = mapper.selLocationCodeList(param);
        LocationCodeEntity code = locationList.get(0);


        InsertEntity param2 = new InsertEntity();
        param2.setIntrn_area_code(code.getIntrn_area_code());
        param2.setArr(list);

        mapper.insApartmentInfoArr(param2);

    }
}
