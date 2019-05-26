package com.kurdov.task4.replacement_with_key.data_store;

public interface DataService <T> {
    T read(String filename);
    void write(String filename);
}
