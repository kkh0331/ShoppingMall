package pda.shoppingmall.product;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.product.dto.DeleteProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsReqDTO;
import pda.shoppingmall.product.dto.FindProductsResDTO;
import pda.shoppingmall.product.dto.RegisterProductReqDTO;
import pda.shoppingmall.utils.ApiUtils;

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
        Product resultProduct = productService.findProduct(id);
        return new ResponseEntity<>(ApiUtils.success(resultProduct), HttpStatus.OK);
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
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiUtils.success(null));
    }

    @PostMapping("/delete")
    public ResponseEntity deleteProducts(@Valid @RequestBody DeleteProductsReqDTO deleteProductsReqDTO){

        log.info("deleteProductsReqDTO : {}", deleteProductsReqDTO);

        productService.deleteProducts(deleteProductsReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiUtils.success(null));
    }

}
