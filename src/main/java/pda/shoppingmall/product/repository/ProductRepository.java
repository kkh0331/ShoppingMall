package pda.shoppingmall.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pda.shoppingmall.product.Product;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Long id);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
    void deleteById(Long id);
    void deleteAllById(Iterable<? extends Long> productIds);
    List<Product> findAllById(Iterable<Long> productIds);
}
