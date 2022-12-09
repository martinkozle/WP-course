package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    void deleteByName(String name);
}
