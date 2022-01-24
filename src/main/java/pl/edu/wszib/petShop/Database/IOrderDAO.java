package pl.edu.wszib.petShop.Database;

import pl.edu.wszib.petShop.Model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int id);
}
