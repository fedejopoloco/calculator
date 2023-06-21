package com.tenpo.calculator.api.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class LogUtil {
    private static final Logger log = LoggerFactory.getLogger(LogUtil.class);

    public LogUtil() {
    }

    public static String processMessage(String payload) {
        if (Objects.nonNull(payload) && !payload.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                Map<String, Object> map = (Map)mapper.readValue(payload, new TypeReference<Map<String, Object>>() {
                });
                return mapper.writeValueAsString(map);
            } catch (IOException var3) {
                log.debug("There was an error processing payload :: " + var3);
                return payload;
            }
        } else {
            return null;
        }
    }

    public static Object processPayload(String payload) {
        if (!Objects.isNull(payload) && !payload.isEmpty()) {
            try {
                return (new ObjectMapper()).readTree(payload);
            } catch (IOException var2) {
                log.debug("Error parsing payload in provider request builder :: " + var2);
                return null;
            }
        } else {
            return payload;
        }
    }
}