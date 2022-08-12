package com.anika.mobilefinancialservice.converter;

import com.anika.mobilefinancialservice.utils.Util;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author atiQue
 * @since 11'Aug 2022 at 10:29 PM
 */

@Converter
public class AccountNumberConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {

        if (StringUtils.hasLength(attribute)) {
            return Util.encode(attribute);
        } else {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        if (StringUtils.hasLength(dbData)) {
            return Util.decode(dbData);
        } else {
            return null;
        }
    }
}
