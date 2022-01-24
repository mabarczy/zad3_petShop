package pl.edu.wszib.petShop.Services;


import pl.edu.wszib.petShop.Model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}