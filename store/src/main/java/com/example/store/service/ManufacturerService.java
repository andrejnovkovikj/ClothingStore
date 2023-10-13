package com.example.store.service;

import com.example.store.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    Manufacturer findById(Long id);
    Manufacturer findByName(String name);
    List<Manufacturer> listAll();
    Manufacturer create(String name, String description, String email);
    Manufacturer update(Long id,String name, String description, String email);
    Manufacturer delete(Long id);
}
