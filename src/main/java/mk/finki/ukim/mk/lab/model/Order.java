package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "balloon_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String balloonColor;
    private String balloonSize;
    @ManyToOne(fetch = FetchType.EAGER)
    ShoppingCart shoppingCart;

    public Order(String balloonColor, String balloonSize, ShoppingCart shoppingCart) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.shoppingCart = shoppingCart;
    }

    public Order() {

    }
}
