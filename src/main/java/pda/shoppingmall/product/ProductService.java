package pda.shoppingmall.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pda.shoppingmall.exception.NotDeleteException;
import pda.shoppingmall.product.dto.DeleteProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsResDTO;
import pda.shoppingmall.product.dto.RegisterProductReqDTO;
import pda.shoppingmall.product.repository.ProductRepository;
import pda.shoppingmall.utils.PageNation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    @Transactional
    public Product registerProduct(RegisterProductReqDTO registerProductReqDTO){
        Product reqProduct = registerProductReqDTO.convertToEntity();
        Product savedProduct = productRepository.save(reqProduct);
        log.info("savedProduct : {}",savedProduct);
        // TODO 예러 처리... 어찌해야 할까...
        return savedProduct;
    }

    public Product findProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 id에 해당하는 Product을 찾을 수 없습니다."));
    }

    public FindProductsResDTO findProducts(FindProductsReqDTO findProductsReqDTO) {

        Page<Product> page = findProductsByCategoryId(findProductsReqDTO);

        if(page.isEmpty()){
            throw new NoSuchElementException("해당 조건에 맞는 products가 서버에 없습니다.");
        }

        FindProductsResDTO findProductsResDTO = new FindProductsResDTO(
                new PageNation(findProductsReqDTO.getCurrentPage(), page.getTotalElements()),
                page.stream().toList()
        );

        log.info("findProductsResDTO : {}", findProductsResDTO);

        return findProductsResDTO;
    }


    private Page<Product> findProductsByCategoryId(FindProductsReqDTO findProductsReqDTO){
        int limit = findProductsReqDTO.getLimit();
        int currentPage = findProductsReqDTO.getCurrentPage();
        Integer categoryId = findProductsReqDTO.getCategoryId();
        Pageable pageable = PageRequest.of(currentPage-1, limit);
        if(categoryId == null){
            return productRepository.findAll(pageable);
        }
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

        Optional<Product> deletedProduct = productRepository.findById(id);
        if(!deletedProduct.isEmpty())
            throw new NotDeleteException("서버에서 Product 삭제 과정에서 오류가 발생했습니다.");
    }

    @Transactional
    public void deleteProducts(DeleteProductsReqDTO deleteProductsReqDTO) {
        List<Long> productIds = deleteProductsReqDTO.getProductIds();
        productRepository.deleteAllById(productIds);
        List<Product> deletedProducts = productRepository.findAllById(productIds);
        if (!deletedProducts.isEmpty()) {
            throw new NotDeleteException("서버 오류로 인해 삭제가 정상적으로 진행되지 않았습니다.");
        }
    }
}
