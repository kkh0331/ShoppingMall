package pda.shoppingmall.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<Product, Long> {

//    Optional<Product> save(Product product);
//    Optional<Product> findById(Long id);
//    Optional<List<Product>> findAll(int limit, int currentPage);
//    Optional<List<Product>> findAll(int limit, int currentPage, int categoryId);
//    void deleteById(Long id);
//    void deleteProducts(List<Long> productIds);

}
