package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Long count() {
        return manufacturerRepository.count();
    }

    @Override
    public Manufacturer save(String name, String country) {
        return save(name, country, "");
    }

    @Override
    public Manufacturer save(String name, String country, String address) {
        return manufacturerRepository.save(new Manufacturer(name, country, address));
    }

    @Override
    public List<Manufacturer> saveAll(List<Manufacturer> manufacturers) {
        return manufacturerRepository.saveAll(manufacturers);
    }
}
