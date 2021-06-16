package com.koreait.spring2;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApartPriceMapper {
    int insApartmentInfoArr(InsertEntity param);
    List<LocationCodeEntity> selLocationCodeList(SearchDTO param);
    List<ResultEntity> selApartmentInfoList(SearchDTO param);
}
