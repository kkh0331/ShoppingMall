package pda.shoppingmall.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.utils.Validator;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private ProductService productService;

    @PostMapping("")
    public ResponseEntity registerProduct(@RequestBody Product product){

        if(Validator.isAlpha(product.getName()) &&
                Validator.isNumber(product.getPrice()) &&
                Validator.isNumber(product.getCategoryId())
        ){
            Product savedProduct = productService.registerProduct(product);

            log.info(product.getName());

            if(savedProduct == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
//            try{
//                System.out.println(savedProduct.getName());
//            } catch (NullPointerException ex){
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable(value = "id") int id){

        if(!Validator.isNumber(id)){ // 400 요청이 잘못되었을 때,
            log.info("id {}", id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product resultProduct = productService.findProduct(id);

        if(resultProduct == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 찾지 못했을 때,

        return new ResponseEntity<>(resultProduct, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> finProducts(
            @RequestParam("limit") int limit,
            @RequestParam("currentPage") int currentPage,
            @RequestParam(value = "categoryId", required = false) Integer categoryId
    ){
        // 정보 추적은 trace, 지금은 info
        log.info("limit = {}", limit);
        log.info("currentPage = {}", currentPage);
        log.info("categoryId = {}", categoryId);

        List<Product> products;

        //TODO null 체크는 어디서 해야할까?
        if(categoryId == null){
            products = productService.findProducts(limit, currentPage);
        } else {
            products = productService.findProducts(limit, currentPage, categoryId);
        }

        if(products == null || products.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") int id){
        if(!Validator.isNumber(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //삭제 성공, 실패 판단하려면 필요한 데이터?
        productService.deleteProduct(id);

        // 상품 조회해서 null 값 떨어지는지 보자!!
        Product product = productService.findProduct(id);
        if(product == null)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteProducts(@RequestBody Map<String, List<Integer>> deleteRequest){
        List<Integer> productIds = deleteRequest.get("productIds");
        log.info("productIds : {}", productIds);

        if(productIds.isEmpty()){
            log.info("productIds가 없어...");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        productService.deleteProducts(productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
