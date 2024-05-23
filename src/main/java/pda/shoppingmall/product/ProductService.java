package pda.shoppingmall.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pda.shoppingmall.product.dto.FindProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsResDTO;
import pda.shoppingmall.product.dto.RegisterProductReqDTO;
import pda.shoppingmall.utils.PageNation;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Optional<Product> findProduct(Long id){
        return productJPARepository.findById(id);
    }

    public FindProductsResDTO findProducts(FindProductsReqDTO findProductsReqDTO) {

        Page<Product> page = findProductsByCategoryId(findProductsReqDTO);

        if(page.isEmpty()){
            throw new NoSuchElementException("해당 조건에 맞는 products는 가져올 수 없습니다.");
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
            return productJPARepository.findAll(pageable);
        }
        return productJPARepository.findAllByCategoryId(categoryId, pageable);
    }


    public void deleteProduct(Long id) {
        productJPARepository.deleteById(id);
    }
//
//    public void deleteProducts(List<Integer> productIds) {
//        productJPARepository.deleteProducts(productIds);
//    }
}
