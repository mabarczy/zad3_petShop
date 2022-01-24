package pl.edu.wszib.petShop.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.petShop.Database.IProductDAO;
import pl.edu.wszib.petShop.Model.OrderPosition;
import pl.edu.wszib.petShop.Model.Product;
import pl.edu.wszib.petShop.Services.ICartService;
import pl.edu.wszib.petShop.Session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    IProductDAO productDAO;

    @Resource
    SessionObject sessionObject;

    public void addProductToCart(int productId) {
        Optional<Product> productBox = this.productDAO.getProductById(productId);

        if(productBox.isEmpty()) {
            return;
        }

        Product product = productBox.get();
        if(product.getQuantity() <= 0) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.getProduct().getId() == productId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(0, product, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}