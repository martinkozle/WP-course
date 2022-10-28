package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Order {
    private Long orderId;
    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAddress;

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress) {
        this.orderId = (long) (Math.random() * 1000000);
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }
}
