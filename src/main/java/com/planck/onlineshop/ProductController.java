package com.planck.onlineshop;

import com.planck.DAO.CustomerDAO;
import com.planck.DAO.ProductDAO;
import com.planck.Model.Customer;
import com.planck.Model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductDAO productDAO = new ProductDAO();

    @RequestMapping("/list")
    public ArrayList<Product> listProducts(){
        return productDAO.listProducts();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        productDAO.createProduct(product);
        return product;
    }

    // Read = GET
    @GetMapping(value = "{productId}")
    public Product getProductById(@PathVariable("productId") long productId) {
        return productDAO.searchProduct(ProductDAO.SQL_PRODUCT_ID, productId+"");
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        productDAO.updateProduct(product);
        return product;
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable("productId") long productId) {
        productDAO.deleteProduct(productId);
    }
}
