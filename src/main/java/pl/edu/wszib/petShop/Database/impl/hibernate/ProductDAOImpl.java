package pl.edu.wszib.petShop.Database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.petShop.Database.IProductDAO;
import pl.edu.wszib.petShop.Model.Product;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAOImpl implements IProductDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.petShop.Model.Product");
        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Product> getProductById(int bookId) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.petShop.Model.Product WHERE id = :id");
        query.setParameter("id", bookId);
        try {
            Product product = query.getSingleResult();
            session.close();
            return Optional.of(product);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
