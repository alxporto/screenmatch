package com.datalastix.screenmatch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements iConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T getDataJSON(String json, Class<T> seriesclass) {
        try {
            return mapper.readValue(json, seriesclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
