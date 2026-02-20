package com.example.collectibles.controllers;

import java.util.List;
import com.example.collectibles.beans.Product;
import com.example.collectibles.beans.Filter;
import com.example.collectibles.dao.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.ArrayList;
import com.example.collectibles.beans.ProductCategory;

@Controller
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    private Executor asyncExecutor;

    public ProductController(ProductRepository productRepository, Executor asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
        this.productRepository = productRepository;
    }

    @PostMapping("/search")
    public String search(@RequestParam("searchString") String keyword, Model model) {
        List<Product> products = productRepository.searchByName(keyword);
        model.addAttribute("products", products);
        model.addAttribute("searchedFor", keyword);
        return "search-results";
    }

    @GetMapping("/getAllProducts")
    public DeferredResult<String> getAllProducts(Model model) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncExecutor.execute(() -> {
            model.addAttribute("products", getProducts());
            model.addAttribute("filter", new Filter());
            deferredResult.setResult("product-list");
        });
        return deferredResult;
    }

    @PostMapping("/filterProducts")
    public String filterProductsByProductType(@ModelAttribute("filter") Filter filter, Model model) {
        List<Product> filteredProducts = new ArrayList<>();
        List<String> selectedTypes = filter.getSelectedType();
        for(String token : selectedTypes) {
            if(token.equals("ALL")) {
                productRepository.findAll().forEach(product -> filteredProducts.add(product));
                break;
            } else {
                int categoryId = ProductCategory.valueOf(token).getId();
                filteredProducts.addAll(productRepository.searchByCategoryId(categoryId));
            }
        }
        model.addAttribute("products", filteredProducts);
        model.addAttribute("filter", filter);
        return "product-list";
    }


    private Iterable<Product> getProducts() {
        logger.info("Fetching all products from the database...We are doing this spring executor thread!");
        try {
            Thread.sleep(5000); // Simulate a delay in fetching products
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productRepository.findAll();
    }

    @GetMapping("/getProductDetails")
    public String getProductDetails(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productRepository.searchById(productId).stream().findFirst().orElse(null));
        return "product-details";
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model, @SessionAttribute("cart")Map<String, Integer> cart, @RequestParam("productId") String productId, @RequestParam("quantity") int quantity) {
        logger.info("cart: {}", cart);
        if(!cart.containsKey(productId)) {
            cart.put(productId, 0);
        }
        
        cart.put(productId, cart.get(productId) + quantity);
        logger.info("cart after adding product: {}", cart);
        return "redirect:/getProductDetails?id=" + productId;
    }
}
