package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.persistence.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Product getById(@PathVariable("id") long id) {
        Optional<Product> optionalProduct = productService.getById(id);
        return optionalProduct.orElseThrow(() -> new NotFoundException("No product with id: " + id));
    }

    @PostMapping()
    public void postProduct(@RequestBody Product product) {
        product.checkValidBusinessData();
        productService.insert(product);
    }

    @PutMapping()
    public void putProduct(@RequestBody Product product) {
        product.checkValidBusinessData();
        productService.update(product);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                            .setErrorCode("rest-001")
                            .setLocalizedMessage(exception.getLocalizedMessage())
                            .setMessage(exception.getMessage())
                            .setStackTrace(exception.getStackTrace())
                            .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                            .setErrorCode("rest-002")
                            .setLocalizedMessage(exception.getLocalizedMessage())
                            .setMessage(exception.getMessage())
                            .setStackTrace(exception.getStackTrace())
                            .build()
                , HttpStatus.BAD_REQUEST);
    }


}
