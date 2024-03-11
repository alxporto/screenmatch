package com.datalastix.screenmatch.services;

public interface iConvertData {
    <T> T getDataJSON(String json, Class<T> seriesclass);
}
