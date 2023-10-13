package com.example.store.service.impl;

import com.example.store.model.Manufacturer;
import com.example.store.model.exceptions.InvalidProductIdException;
import com.example.store.repository.ManufacturerRepository;
import com.example.store.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    @Override
    public Manufacturer findByName(String name) {
        return this.manufacturerRepository.findAllByName(name);
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer create(String name, String description, String email) {
        Manufacturer manufacturer = new Manufacturer(name,description,email);
        return this.manufacturerRepository.save(manufacturer);

    }

    @Override
    public Manufacturer update(Long id, String name, String description, String email) {
        Manufacturer manufacturer=findById(id);
        manufacturer.setName(name);
        manufacturer.setDescription(description);
        manufacturer.setEmail(email);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer delete(Long id) {
        Manufacturer manufacturer=findById(id);
        this.manufacturerRepository.delete(manufacturer);
        return manufacturer;
    }
}
