package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.*;
import com.bezkoder.spring.login.payload.response.*;
import com.bezkoder.spring.login.repository.CategoryRepository;
import com.bezkoder.spring.login.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponse> productResponseList = getAllProductResponses();
        return ResponseEntity.ok()
                .body(productResponseList);
    }

    @GetMapping("/get_sorted/{sortingOrder}")
    public ResponseEntity<?> getAllProductsSorted(@PathVariable Integer sortingOrder) {
        return ResponseEntity.ok().body(getAllProductResponsesSorted(sortingOrder));
    }

    @GetMapping("/get_top_products")
    public ResponseEntity<?> getTopProducts() {
        return ResponseEntity.ok().body(getTopProductsResponses());
    }

    @GetMapping("/get_product_by_query/{query}")
    public ResponseEntity<?> getProductsByQuery(@PathVariable String query) {
        return ResponseEntity.ok().body(getAllProductResponseByQuery(query));
    }

    private List<ProductResponse> getAllProductResponseByQuery(String query) {
        Set<Product> sourceProducts = productRepository.findProductsWithSearchQuery(query);
        return convertFromProductEntity(sourceProducts.stream().toList());
    }

    private List<ProductResponse> getAllProductResponses() {
        List<Product> sourceProducts = productRepository.findAll();
        return convertFromProductEntity(sourceProducts);
    }

    private List<ProductResponse> getAllProductResponsesSorted(Integer sortingOrder) {
        List<Product> sourceProducts;
        if(sortingOrder == 0) {
            sourceProducts = productRepository.findAllByOrderByPriceAsc();
        } else {
            sourceProducts = productRepository.findAllByOrderByPriceDesc();
        }
        return convertFromProductEntity(sourceProducts);
    }

    private List<ProductResponse> getTopProductsResponses(){
        List<Product> sourceProducts = productRepository.findTopProducts();
        return convertFromProductEntity(sourceProducts);
    }

    private List<ProductResponse> convertFromProductEntity(List<Product> sourceProducts) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product: sourceProducts) {
            CategoryResponse categoryResponse = getCategoryById(product.getCategory().getId());

            List<String> images = new ArrayList<>();
            for (ProductImage productImage: product.getImages()) {
                images.add(productImage.getImageSrc());
            }

            List<PairAttributeWithGroup> attributes = new ArrayList<>();
            for (Attribute attribute: product.getAttributes()) {
                AttributeResponse attributeResponse = new AttributeResponse(
                        Math.toIntExact(attribute.getId()),
                        attribute.getName()
                );

                HashSet<AttributeResponse> attributeResponses = new HashSet<>();
                for (Attribute attributeForAttributeGroup: attribute.getAttributeGroup().getAttributes()) {
                    if(attributeForAttributeGroup.getId() == attributeResponse.getId()) {
                        attributeResponses.add(attributeResponse);
                    } else {
                        attributeResponses.add(
                                new AttributeResponse(
                                        Math.toIntExact(attributeForAttributeGroup.getId()),
                                        attributeForAttributeGroup.getName()
                                )
                        );
                    }
                }
                attributes.add(
                        new PairAttributeWithGroup(
                                new AttributeGroupResponse(
                                        Math.toIntExact(attribute.getAttributeGroup().getId()),
                                        attribute.getAttributeGroup().getName(),
                                        attributeResponses
                                ),
                                attributeResponse
                        )
                );
            }

            productResponseList.add(new ProductResponse(
                    Math.toIntExact(product.getId()),
                    product.getName(),
                    product.getPrice(),
                    product.getProductStatus().getStatusText(),
                    product.getGuaranteeLengthMonth(),
                    product.getReturnExchangeLength(),
                    categoryResponse,
                    product.getDescription(),
                    product.getProductImageSrc(),
                    images,
                    attributes,
                    product.getTags()
            ));
        }
        return productResponseList;
    }

    private CategoryResponse getCategoryById(Long id) {
        CategoryResponse categoryResponse = null;
        if(id != null) {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()) {
                CategoryResponse parentCategory = getCategoryById(category.get().getParentCategoryId());
                Set<AttributeGroupResponse> attributeGroupSet = new HashSet<>();
                for (AttributeGroup attributeGroup: category.get().getAttributeGroups()) {
                    Set<AttributeResponse> attributes = new HashSet<>();
                    for (Attribute attr: attributeGroup.getAttributes()) {
                        attributes.add(new AttributeResponse(
                                Math.toIntExact(attr.getId()),
                                attr.getName()
                        ));
                    }
                    attributeGroupSet.add(new AttributeGroupResponse(
                            Math.toIntExact(attributeGroup.getId()),
                            attributeGroup.getName(),
                            attributes
                    ));
                }
                categoryResponse = new CategoryResponse(
                        Math.toIntExact(category.get().getId()),
                        category.get().getName(),
                        parentCategory,
                        attributeGroupSet
                );
            }
        }
        return categoryResponse;
    }
}
