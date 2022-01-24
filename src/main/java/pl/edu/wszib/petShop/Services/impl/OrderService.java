package pl.edu.wszib.petShop.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.petShop.Database.IOrderDAO;
import pl.edu.wszib.petShop.Database.IProductDAO;
import pl.edu.wszib.petShop.Model.Order;
import pl.edu.wszib.petShop.Model.OrderPosition;
import pl.edu.wszib.petShop.Model.Product;
import pl.edu.wszib.petShop.Services.IOrderService;
import pl.edu.wszib.petShop.Session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IProductDAO productDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Product> productBox = this.productDAO.getProductById(orderPosition.getProduct().getId());
            if(productBox.isPresent()) {
                productBox.get().setQuantity(productBox.get().getQuantity() - orderPosition.getQuantity());
                this.productDAO.updateProduct(productBox.get());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
