package org.project.study.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        if (attribute == null) {
            return "";
        }
        return attribute.toString();
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            if (StringUtils.isEmpty(dbData)) {
                return JsonNodeFactory.instance.objectNode();
            }
            return objectMapper.readTree(dbData);
        } catch (IOException e) {
            return null;
        }
    }
}
