package com.cns.assignment.converter;

import com.cns.assignment.enums.ProjectStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<ProjectStatus, Integer> {


    @Override
    public Integer convertToDatabaseColumn(ProjectStatus attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(Integer dbData) {
       if (dbData == null){
           return null;
       }
       return Stream.of(ProjectStatus.values())
               .filter(s -> s.getCode() == dbData)
               .findFirst()
               .orElseThrow(IllegalArgumentException::new);
    }
}
