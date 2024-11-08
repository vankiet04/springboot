package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.ColorService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import net.enjoy.springboot.registrationlogin.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.html.impl.Color;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private ColorService colorService;
    private SizeService sizeService;

    @Autowired
    public void setProductService(ProductService productService, CategoryService categoryService, ColorService colorService, SizeService sizeService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.colorService = colorService;
        this.sizeService = sizeService;
    }

    @GetMapping("/test")
    public String t() {
        return "home";
    }

    @GetMapping("/admin")
    public String checkVar() {
        return "admin";
    }
    
    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.findAllProduct(pageable);
        model.addAttribute("categoryService", categoryService);
        model.addAttribute("products", products);

        //get all category
        model.addAttribute("categories", categoryService.findAllCategory());
        return "home";
    }

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size,
    @RequestParam(value = "sizeId", required = false) Long sizeId, @RequestParam(value = "colorId", required = false) Long colorId,
    @RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(defaultValue="0") Long minPrice,
    @RequestParam(defaultValue="999999999")Long maxPrice, @RequestParam(value = "name", required = false) String name,
     @RequestParam(value = "sort", required = false) String sort) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products;
        if (sort != null && sort.equals("asc")) 
            products = productService.searchProductPriceAsc(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        else if (sort != null && sort.equals("desc")) 
            products = productService.searchProductPriceDesc(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        else
            products = productService.searchProduct(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
            System.out.println("products1 = " + products);
        // get all category
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("colors", colorService.findAllColor());
        model.addAttribute("sizes", sizeService.findAllSize());
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/search")
    public String searchProducts( @RequestParam(value = "name", required = false) String name,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products;
        products = productService.searchProduct( null, null, null, null, null, pageable,name);
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("colors", colorService.findAllColor());
        model.addAttribute("sizes", sizeService.findAllSize());
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/product_detail")
    public String productDetail(Model model, @RequestParam(value = "id", required = false) Long idProduct) {
        ProductDto product = productService.findById(idProduct);
        model.addAttribute("product", product);

        List<ProductDetailDto> productDetails = productService.findDetailById(idProduct);
        model.addAttribute("productDetails", productDetails);
        return "product-detail";
    }

}
