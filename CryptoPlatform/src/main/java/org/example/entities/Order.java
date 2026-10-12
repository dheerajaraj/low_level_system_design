package org.example.entities;

import java.util.UUID;

public class Order {

    public enum OrderType {
        LIMIT,
        MARKET;
    }

    public enum Side {
        BUY,
        SELL;
    }

    public enum Status {
        OPEN,
        PARTIALLY_FILLED,
        FILLED,
        CANCELLED;
    }


    public long id;
    public UUID userId;
    public float price;
    public int quantity;
    public long ts;
    public OrderType orderType;
    public Side side;
    public Status status;

    public void processOrder(Order sellOrder) {
        if (sellOrder.quantity > this.quantity) {
            sellOrder.quantity -= this.quantity;
            sellOrder.status = Order.Status.PARTIALLY_FILLED;
            this.status = Order.Status.FILLED;
            return;
        }
        this.quantity -= sellOrder.quantity;
        this.status = (this.quantity == 0) ? this.status = Order.Status.FILLED : Order.Status.PARTIALLY_FILLED;
        sellOrder.status = Order.Status.FILLED;
    }

}
