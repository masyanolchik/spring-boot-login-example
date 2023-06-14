package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByOrderByPriceAsc();

    public List<Product> findAllByOrderByPriceDesc();

    @Query(value = TOP_PRODUCTS_QUERY, nativeQuery = true)
    public List<Product> findTopProducts();

    @Query(value = SEARCH_PRODUCT_QUERY, nativeQuery = true)
    public Set<Product> findProductsWithSearchQuery(@Param("query") String query);

    public static String TOP_PRODUCTS_QUERY = "SELECT * FROM dyplom_db.products, (SELECT product_tags.product_id FROM dyplom_db.tags INNER JOIN product_tags ON dyplom_db.tags.id = product_tags.tag_id WHERE dyplom_db.tags.name LIKE \"top_product\") as top_products WHERE dyplom_db.products.id = top_products.product_id;";
    public static String SEARCH_PRODUCT_QUERY = "SELECT * FROM dyplom_db.products\n" +
            "INNER JOIN dyplom_db.product_tags ON dyplom_db.product_tags.product_id = dyplom_db.products.id\n" +
            "INNER JOIN dyplom_db.tags ON tag_id = dyplom_db.tags.id\n" +
            "WHERE dyplom_db.products.name LIKE %:query% OR dyplom_db.products.price LIKE %:query% \n" +
            "      OR dyplom_db.tags.name LIKE %:query% ;";
}
