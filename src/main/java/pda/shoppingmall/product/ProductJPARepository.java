package pda.shoppingmall.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductJPARepository extends JpaRepository<Product, Long> {

//    Optional<Product> save(Product product);
//    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);

//    Optional<List<Product>> findAll(int limit, int offset, int categoryId);
//    void deleteById(Long id);
//    void deleteProducts(List<Long> productIds);

}
