package org.example;

import org.example.entities.Order;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TradingSystem {

    public final PriorityQueue<Order> buyOrder;
    public final PriorityQueue<Order> sellOrder;
    public final LinkedList<Order> completedOrders;


    public TradingSystem() {
        this.buyOrder = new PriorityQueue<>(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                if (o1.price == o2.price) {
                    return (int) (o2.ts - o1.ts);
                }
                return Float.compare(o2.price, o1.price);
            }
        });
        this.sellOrder = new PriorityQueue<>(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                if (o1.price == o2.price) {
                    return (int) (o2.ts - o1.ts);
                }
                return Float.compare(o1.price, o2.price);
            }
        }
        );
        this.completedOrders = new LinkedList<>();
    }

    public void placeOrder(Order order) {
        switch (order.side) {
            case BUY:
                placeOrder(order, buyOrder, sellOrder);
                break;
            case SELL:
                placeOrder(order, sellOrder, buyOrder);
                break;
            default:
                return;
        }
    }

    private void placeOrder(Order order, PriorityQueue<Order> selectedQ, PriorityQueue<Order> q){
        if(q.isEmpty() || q.peek().price > order.price) {
            selectedQ.add(order);
            return;
        }
        if(q.peek().quantity > order.quantity) {
            order.processOrder(q.peek());
            this.completedOrders.add(order);
            return;
        }

        Order sellOrder = q.poll();
        order.processOrder(sellOrder);
        this.completedOrders.add(sellOrder);
        if(order.quantity == 0) {
            this.completedOrders.add(order);
        } else {
            selectedQ.add(order);
        }
    }
}
