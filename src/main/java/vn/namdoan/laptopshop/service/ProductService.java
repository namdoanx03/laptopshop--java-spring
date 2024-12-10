package vn.namdoan.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.namdoan.laptopshop.domain.Product;
import vn.namdoan.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // function tao moi product
    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> fetchProductById(long id) {
        /*
         * bien optional co 2 trang thai:
         * 1 , co gia tri, su dung ham get() no se tra ra doi tuong muon thao tac
         * 2, khong co gia tri
         */
        return this.productRepository.findById(id);
    }
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
