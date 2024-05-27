package pda.shoppingmall.product.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pda.shoppingmall.product.Product;

import java.util.*;

@Repository
@Slf4j
public class ProductMapRepository implements ProductRepository {

    private Map<Long, Product> productTable = new HashMap<>();
    private Long id = 1L;

    @Override
    public Product save(Product product) {
        product.setId(id++);
        productTable.put(product.getId(), product);
        return productTable.get(product.getId());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productTable.get(id));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        int limit = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        List<Product> products = productTable.values()
                                        .stream()
                                        .skip((long)currentPage *limit)
                                        .limit(limit)
                                        .toList();
        int count = productTable.size();
        return new PageImpl<>(products, pageable, count);
    }

    @Override
    public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable) {
        int limit = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        List<Product> products = productTable.values()
                .stream()
                .filter(product -> Objects.equals(product.getCategoryId(), categoryId))
                .skip((long)currentPage *limit)
                .limit(limit)
                .toList();
        int count = (int) productTable.values().stream()
                .filter(product -> Objects.equals(product.getCategoryId(), categoryId))
                .count();
        return new PageImpl<>(products, pageable, count);
    }

    @Override
    public void deleteById(Long id) {
        productTable.remove(id);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> productIds) {
        productIds.forEach(productId -> {
            productTable.remove(productId);
        });
    }

    @Override
    public List<Product> findAllById(Iterable<Long> productIds) {
        List<Product> foundProducts = new ArrayList<>();
        productIds.forEach(productId -> {
            if(productTable.containsKey(productId)){
                foundProducts.add(productTable.get(productId));
            }
        });
        return foundProducts;
    }

}
