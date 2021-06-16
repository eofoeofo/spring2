package com.koreait.spring2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultEntity {
    private int i_ai; // property 하지 않은 이유는 여기서 넘어오는 값이 아님

    @JsonProperty("거래금액") // 거래금액과 mapping
    private String deal_amount;

    @JsonProperty("건축년도")
    private String build_year;

    @JsonProperty("년")
    private String deal_year;

    @JsonProperty("월")
    private String deal_month;

    @JsonProperty("일")
    private String deal_day;

    @JsonProperty("법정동")
    private String dong;

    @JsonProperty("아파트")
    private String apartment_name;

    @JsonProperty("전용면적")
    private float area_for_exclusive_use;

    @JsonProperty("지번")
    private String parcel_num;

    @JsonProperty("층")
    private int flr;

    private int intrn_area_code;

    public void setDeal_amount(String str){
        this.deal_amount = str.trim().replace(",","");
        // deal_aount값 빈칸 제거 및 콤마 추가
    }
}
