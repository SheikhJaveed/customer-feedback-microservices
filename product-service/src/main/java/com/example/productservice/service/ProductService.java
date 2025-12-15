package com.example.productservice.service;

import com.example.productservice.client.AnalyticsClient;
import com.example.productservice.client.AnalyticsEventDto;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final AnalyticsClient analyticsClient; // 1. Add Client

    // 2. Inject Client via Constructor
    public ProductService(ProductRepository repository, AnalyticsClient analyticsClient) {
        this.repository = repository;
        this.analyticsClient = analyticsClient;
    }

    // 3. Update method to accept Token
    public ProductResponse createProduct(ProductRequest req, String token) {
        System.out.println(">>> STARTING CREATE PRODUCT: " + req.getName());

        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setStock(req.getStock());
        Product saved = repository.save(p);

        System.out.println(">>> PRODUCT SAVED TO DB. ID: " + saved.getId());

        // 4. Send Event to Analytics
        try {
            System.out.println(">>> ATTEMPTING TO SEND ANALYTICS EVENT...");
            AnalyticsEventDto event = new AnalyticsEventDto(
                    "PRODUCT_CREATED",
                    "Created product: " + saved.getName()
            );
            // We forward the user's token so Analytics knows WHO created it
            analyticsClient.logEvent(token, event);
            System.out.println(">>> SUCCESS: Sent PRODUCT_CREATED event to Analytics");
        } catch (Exception e) {
            System.err.println(">>> ERROR: Failed to send analytics event!");
            e.printStackTrace();
        }

        return mapToDto(saved);
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ProductResponse mapToDto(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock());
    }
}