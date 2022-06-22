package com.spiderman.blogsweb.blogs.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter implements AttributeConverter<BlogType, String> {

    @Override
    public String convertToDatabaseColumn(BlogType blogType) {
        if (blogType == null){
            return null;
        }
        return blogType.value();
    }

    @Override
    public BlogType convertToEntityAttribute(String name) {
        if (!StringUtils.hasText(name)){
            return null;
        }
        for (BlogType blogtype:BlogType.values()) {
            if (blogtype.value().equals(name)){
                return blogtype;
            }
        }
        throw new IllegalArgumentException();
    }
}
