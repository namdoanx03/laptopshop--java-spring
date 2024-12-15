package vn.namdoan.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.namdoan.laptopshop.domain.Cart;
import vn.namdoan.laptopshop.domain.CartDetail;
import vn.namdoan.laptopshop.domain.Product;
import vn.namdoan.laptopshop.domain.User;
import vn.namdoan.laptopshop.repository.CartDetailRepository;
import vn.namdoan.laptopshop.repository.CartRepository;
import vn.namdoan.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

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

    public void handleAddProductToCart(String email, long productId) {

        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có Cart chưa ? nếu chưa -> tạo mới
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                // tạo mới cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(1);

                cart = this.cartRepository.save(otherCart);
            }

            // save cart_detail
            // tìm product by id

            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                CartDetail cd = new CartDetail();
                cd.setCart(cart);
                cd.setProduct(realProduct);
                cd.setPrice(realProduct.getPrice());
                cd.setQuantity(1);
                this.cartDetailRepository.save(cd);
            }

        }
    }

}