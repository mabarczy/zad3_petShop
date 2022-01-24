package pl.edu.wszib.petShop.Database;

import pl.edu.wszib.petShop.Model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProducts();
    Optional<Product> getProductById(int id);
    void updateProduct(Product product);
}
