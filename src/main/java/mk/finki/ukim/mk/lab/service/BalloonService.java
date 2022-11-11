package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text);

    Balloon save(String name, String description, Long manufacturerId);

    void deleteById(Long id);

    Balloon findById(Long id);
}
