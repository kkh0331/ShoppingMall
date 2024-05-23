package pda.shoppingmall.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.exception.NoDeleteException;
import pda.shoppingmall.product.dto.FindProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsResDTO;
import pda.shoppingmall.product.dto.RegisterProductReqDTO;
import pda.shoppingmall.utils.ApiUtils;
import pda.shoppingmall.utils.Validator;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private ProductService productService;

    @PostMapping("")
    public ResponseEntity registerProduct(@Valid @RequestBody RegisterProductReqDTO registerProductReqDTO){

        log.info("product : {}", registerProductReqDTO);

        Product savedProduct = productService.registerProduct(registerProductReqDTO);

        log.info("savedProduct : {}", savedProduct);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(savedProduct));
    }

    //TODO id가 양수가 아니면 에러처리가 안됨...
    @GetMapping("/{id}")
    public ResponseEntity findProduct(@PathVariable(value = "id") Long id){
        log.info("id : {}",id);
        Optional<Product> resultProduct = productService.findProduct(id);
        if(resultProduct.isEmpty()){
            throw new NoSuchElementException("해당 id에 해당하는 Product을 찾을 수 없습니다.");
        }
        return new ResponseEntity<>(ApiUtils.success(resultProduct.get()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity finProducts(
            @RequestParam("limit") int limit,
            @RequestParam("currentPage") int currentPage,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            FindProductsReqDTO findProductsReqDTO
    ){
        // 정보 추적은 trace, 지금은 info
        log.info("findProductsReqDTO = {}", findProductsReqDTO);

        FindProductsResDTO findProductsResDTO = productService.findProducts(findProductsReqDTO);

        return new ResponseEntity<>(ApiUtils.success(findProductsResDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Long id){

        // 이미 상품이 없는 상태일 경우...
        Optional<Product> product = productService.findProduct(id);
        if(product.isEmpty()){
            throw new NoSuchElementException("이미 삭제된 Product입니다.");
        }

        //삭제 성공, 실패 판단하려면 필요한 데이터?
        productService.deleteProduct(id);

        // 상품 조회해서 null 값 떨어지는지 보자!!x
        Optional<Product> deletedProduct = productService.findProduct(id);
        if(deletedProduct.isEmpty())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiUtils.success(null));

        throw new NoDeleteException("서버에서 Product 삭제 과정에서 오류가 발생했습니다.");
    }

//    @PostMapping("/delete")
//    public ResponseEntity deleteProducts(@RequestBody Map<String, List<Integer>> deleteRequest){
//        List<Integer> productIds = deleteRequest.get("productIds");
//        log.info("productIds : {}", productIds);
//
//        if(productIds.isEmpty()){
//            log.info("productIds가 없어...");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        productService.deleteProducts(productIds);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
