package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();

    Long count();

    List<Balloon> searchByNameOrDescription(String text);

    Balloon save(String name, String description, Long manufacturerId);

    List<Balloon> saveAll(List<Balloon> balloons);

    void deleteById(Long id);

    Optional<Balloon> findById(Long id);
}
