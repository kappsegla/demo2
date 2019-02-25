package se.iths.martin.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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



}
