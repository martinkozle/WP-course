package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryManufacturerRepository {
    private final List<Manufacturer> manufacturers;

    public InMemoryManufacturerRepository() {
        this.manufacturers = new ArrayList<>(List.of(
                new Manufacturer("Balloon Factory Skopje", "Macedonia", "Skopje"),
                new Manufacturer("Strumica Balloons", "Macedonia", "Strumica"),
                new Manufacturer("Balloons LA", "USA", "Los Angeles"),
                new Manufacturer("Balloons of London", "United Kingdom", "London"),
                new Manufacturer("Balloons made in China", "China", "Shanghai")
        ));
    }

    public List<Manufacturer> findAll() {
        return this.manufacturers;
    }

    public Manufacturer findById(Long id) {
        return this.manufacturers.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
