package com.datalastix.screenmatch.service;

public interface IDataConversion {
    <T> T getData(String json, Class<T> classes);
}
