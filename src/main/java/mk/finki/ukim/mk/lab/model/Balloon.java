package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Balloon {
    private Long id;
    private String name;
    private String description;

    public Balloon(String name, String description) {
        this.id = (long) (Math.random() * 1000000);
        this.name = name;
        this.description = description;
    }
}
