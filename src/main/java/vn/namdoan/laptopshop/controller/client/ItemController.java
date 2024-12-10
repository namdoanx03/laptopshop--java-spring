package vn.namdoan.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.namdoan.laptopshop.domain.Product;
import vn.namdoan.laptopshop.service.ProductService;

@Controller
public class ItemController {

    // Mo hinh dependency injection
    // khoi tao 1 ham tao, khoi tao gia tri ban dau cua no, gan gia tri cho no

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
}