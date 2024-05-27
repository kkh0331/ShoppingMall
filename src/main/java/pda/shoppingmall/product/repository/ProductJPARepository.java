package pda.shoppingmall.product.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import pda.shoppingmall.product.Product;

@Primary
public interface ProductJPARepository extends JpaRepository<Product, Long>, ProductRepository {

}
