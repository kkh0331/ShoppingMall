package pda.shoppingmall.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ProductRepository {

    private Map<Integer, Product> productTable = new HashMap<>();
    private int id = 0;

    public Product save(Product product){
        product.setId(id++);
        productTable.put(product.getId(), product);
        return productTable.get(id-1);
    }

    public Product findProduct(int id){
        return productTable.get(id);
    }

    public List<Product> findProducts(int limit, int currentPage) {
        // Map -> Stream -> List
        // TODO 추후 DB 연결 후에
        return productTable.values()
                .stream()
                .skip((long) (currentPage - 1) *limit)
                .limit(limit)
                .toList();
    }

    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
        return productTable.values()
                .stream()
                .filter(product -> product.getCategoryId() == categoryId)
                .skip((long) (currentPage - 1) *limit)
                .limit(limit)
                .toList();
    }

    public void deleteProduct(int id) {
        productTable.remove(id);
    }

    public void deleteProducts(List<Integer> productIds) {
        for(int productId : productIds){
            productTable.remove(productId);
        }
    }
}
