package com.ssafy.api.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CultureDeleteReq {
    String cultureTitle;
    long cultureCategory;
}
