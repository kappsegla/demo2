package se.iths.martin.demo2;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ClientRestAPICalls {

    public void callApi(){
        //RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                "http://localhost:8080/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>(){});
        List<Product> products = response.getBody();

        products.forEach(product -> System.out.println(product.getProductName()));

        Product product = restTemplate.getForObject("http://localhost:8080/products/3", Product.class);

        System.out.println( product.getProductName());

//        //WebClient  from spring-boot-starter-webflux
//        Product product1 = WebClient
//                .create()
//                .method(HttpMethod.GET)
//                .uri("http://localhost:8080/products/3")
//                .retrieve()
//                .bodyToMono(Product.class)
//                .block();
//
//        System.out.println(product1.getProductName());

    }

}
