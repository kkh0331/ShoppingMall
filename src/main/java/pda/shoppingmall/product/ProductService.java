package pda.shoppingmall.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.product.dto.RegisterProductReqDTO;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductJPARepository productJPARepository;

    @Transactional
    public Product registerProduct(RegisterProductReqDTO registerProductReqDTO){
        Product reqProduct = registerProductReqDTO.convertToEntity();
        Product savedProduct = productJPARepository.save(reqProduct);
        log.info("savedProduct : {}",savedProduct);
        // TODO 예러 처리... 어찌해야 할까...
        return savedProduct;
    }

//    public Optional<Product> findProduct(Long id){
//        return productJPARepository.findById(id);
//    }
//
//    public List<Product> findProducts(int limit, int currentPage) {
//        return productJPARepository.findProducts(limit, currentPage);
//
//    }
//
//    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
//        return productJPARepository.findProducts(limit, currentPage, categoryId);
//    }
//
//    public void deleteProduct(int id) {
//        productJPARepository.deleteProduct(id);
//    }
//
//    public void deleteProducts(List<Integer> productIds) {
//        productJPARepository.deleteProducts(productIds);
//    }
}
