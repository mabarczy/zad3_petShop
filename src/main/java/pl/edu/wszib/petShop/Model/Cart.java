package pl.edu.wszib.petShop.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderPosition> orderPositions = new ArrayList<>();

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public double getSum() {
        double sum = 0.0;
        for(OrderPosition orderPosition : this.orderPositions) {
            sum += orderPosition.getQuantity() * orderPosition.getProduct().getPrice();
        }
        return Math.round(sum*100)/100.0;
    }

    public void clearOrderPositions() {
        this.orderPositions = new ArrayList<>();
    }
}