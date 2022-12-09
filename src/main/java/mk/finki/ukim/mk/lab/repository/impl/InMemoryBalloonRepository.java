package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {
    private final List<Balloon> balloons;

    public InMemoryBalloonRepository() {
        this.balloons = new ArrayList<>(List.of(
                new Balloon("Red", "Red balloon"),
                new Balloon("Green", "Green balloon"),
                new Balloon("Blue", "Blue balloon"),
                new Balloon("Yellow", "Yellow balloon"),
                new Balloon("Orange", "Orange balloon"),
                new Balloon("Purple", "Purple balloon"),
                new Balloon("Pink", "Pink balloon"),
                new Balloon("Brown", "Brown balloon"),
                new Balloon("Black", "Black balloon"),
                new Balloon("White", "White balloon")
        ));
    }

    public List<Balloon> findAllBalloons() {
        return this.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return this.balloons.stream()
                .filter(balloon -> balloon.getName().contains(text) || balloon.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Balloon save(Balloon balloon) {
        this.balloons.add(balloon);
        return balloon;
    }

    public void deleteById(Long id) {
        this.balloons.removeIf(balloon -> balloon.getId().equals(id));
    }

    public Balloon findById(Long id) {
        return this.balloons.stream()
                .filter(balloon -> balloon.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
