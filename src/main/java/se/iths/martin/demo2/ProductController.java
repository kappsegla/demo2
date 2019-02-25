package se.iths.martin.demo2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return repository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getOne(@PathVariable Long id){
                return repository.findById(id)
                .orElseThrow( () -> new ProductException("No product with id " + id));
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product ) {
        return repository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/products/{id}")
    public Product change(@RequestBody Product product, @PathVariable Long id){
        return repository.findById(id).map(storedProduct -> {
                    storedProduct.setProductName(product.getProductName());
                    storedProduct.setDescription(product.getDescription());
                    return repository.save(storedProduct);
                }).orElseThrow( () -> new ProductException("No product with id " + id));
    }
}
