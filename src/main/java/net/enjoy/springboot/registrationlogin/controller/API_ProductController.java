package net.enjoy.springboot.registrationlogin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.entity.Product;

import org.h2.expression.function.SysInfoFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//import category
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/products")
public class API_ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getall")
    public List<ProductDto> getAllProducts() {
        return productService.findAllProduct();
    }

   // /api/products/getproduct?page=1
    @GetMapping("/getProductPage")
    public List<ProductDto> getProductWithPage(@RequestParam(defaultValue = "1") int page) {
        return productService.findAllProductWithPage(page);
    }

    private static String UPLOAD_DIR = "./src/main/resources/static/img/products";

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProductAPI(@RequestBody ProductDto productDTO) {
        try {

            String fileName = saveImage(productDTO.getImg());
            productDTO.setImg("/img/products/" + fileName);

            Product product = new Product();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setImg(productDTO.getImg());
            product.setStatus(productDTO.getStatus());

            Category category = categoryService.findById(productDTO.getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            product.setCategory(category);

            productService.addProductAPI(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateProductAPI(@PathVariable Long id, @RequestBody ProductDto productDTO) {
        try {

            if (productDTO.getImg() != null && productDTO.getImg().startsWith("data:image")) {
                String fileName = saveImage(productDTO.getImg());
                productDTO.setImg("/img/products/" + fileName);
            }


            Product existingProduct = productService.findProductById(id);
            if (existingProduct == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            if (productDTO.getImg() != null) {
                existingProduct.setImg(productDTO.getImg());
            }
            existingProduct.setStatus(productDTO.getStatus());
            Category category = categoryService.findById(productDTO.getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            existingProduct.setCategory(category);
            System.out.println("___ Toi da chay toi buoc sua san pham " + existingProduct.getCategory().getId());

            productService.updateProductAPI(existingProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<HttpStatus> updateProductStatus(@PathVariable Long id, @RequestBody ProductDto productDTO) {
        try {
            Product existingProduct = productService.findProductById(id);
            if (existingProduct == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            existingProduct.setStatus(productDTO.getStatus());
            productService.updateProductAPI(existingProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveImage(String base64Image) throws IOException {
        if (base64Image == null || base64Image.isEmpty()) {
            throw new IOException("Image data is empty");
        }

        String[] parts = base64Image.split(",");
        String imageString = parts[1];

        byte[] imageBytes = Base64.getDecoder().decode(imageString);

        String fileName = System.currentTimeMillis() + ".jpg";

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);


        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageBytes);
        }

        return fileName;
    }

    @GetMapping("/getAllProductWithAllDetails")
    public ResponseEntity<List<Map<String, Object>>> getAllProductWithAllDetails() {
        List<Map<String, Object>> products = productService.getAllProductWithAllDetails();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
