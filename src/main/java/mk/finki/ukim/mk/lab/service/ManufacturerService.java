package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Long count();

    Manufacturer save(String name, String country);

    Manufacturer save(String name, String country, String address);

    List<Manufacturer> saveAll(List<Manufacturer> manufacturers);
}
