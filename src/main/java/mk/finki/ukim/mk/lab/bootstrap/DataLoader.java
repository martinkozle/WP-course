package mk.finki.ukim.mk.lab.bootstrap;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ManufacturerService manufacturerService;
    private final BalloonService balloonService;
    private final UserService userService;

    public DataLoader(ManufacturerService manufacturerService, BalloonService balloonService, UserService userService) {
        this.manufacturerService = manufacturerService;
        this.balloonService = balloonService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) {
        if (userService.findByUsername("admin").isEmpty()) {
            userService.register("admin", "admin", "", "admin", LocalDate.of(2000, 1, 1));
        }

        if (userService.findByUsername("testuser").isEmpty()) {
            userService.register("testuser", "testuser", "", "password", LocalDate.of(2000, 1, 1));
        }

        if (manufacturerService.count() == 0) {
            var manufacturers = List.of(
                    new Manufacturer("Balloon Factory Skopje", "Macedonia", "Skopje"),
                    new Manufacturer("Strumica Balloons", "Macedonia", "Strumica"),
                    new Manufacturer("Balloons LA", "USA", "Los Angeles"),
                    new Manufacturer("Balloons of London", "United Kingdom", "London"),
                    new Manufacturer("Balloons made in China", "China", "Shanghai")
            );
            manufacturerService.saveAll(manufacturers);
        }

        if (balloonService.count() == 0) {
            var balloons = List.of(
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
            );
            balloonService.saveAll(balloons);
        }
    }
}
