package com.ruoyi.common.utils;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;

import java.util.*;

/**
 * 国家
 */
public class CountryUtils {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

//    public static void main(String[] args) {
//        String[] locales = Locale.getISOCountries();
//        List<Locale> locales1 = new ArrayList<>();
//        int i = 28;
//        int j = 0;
//        for (String countryCode : locales) {
//            i++;
//            j++;
//            Locale obj = new Locale("", countryCode);
//            locales1.add(obj);
//            StringBuffer coun = new StringBuffer();
//            coun.append("insert into sys_dict_data values(")
//                    .append(i).append(", ")
//                    .append(j).append(",  '")
//                    .append(obj.getDisplayCountry())
//                    .append("',     '").append(obj.getCountry())
//                    .append("',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '")
//                    .append(obj.getDisplayCountry()).append("');");
//            System.out.println(coun.toString());
//
//        }
//
//        System.out.println("Done");
//    }

    public static List<String> getAllCountryCode() {
        String[] locales = Locale.getISOCountries();
        List<String> country = new ArrayList<>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            country.add(obj.getCountry());
        }
        return country;
    }

    public static List<String> getAllCountryName() {
        String[] locales = Locale.getISOCountries();
        List<String> country = new ArrayList<>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            country.add(obj.getDisplayLanguage());
        }
        return country;
    }

    public static List<Map<String, String>> getAllCountryMap() {
        String[] locales = Locale.getISOCountries();
        List<Map<String, String>> country = new ArrayList<>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            Map<String, String> countryMap = new HashMap<>();
            countryMap.put("countryCode", obj.getCountry());
            countryMap.put("countryName", obj.getDisplayCountry());
            country.add(countryMap);
        }
        return country;
    }

}
