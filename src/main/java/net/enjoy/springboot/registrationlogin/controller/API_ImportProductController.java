package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/importproduct")
public class API_ImportProductController {

    @Autowired
    private ImportProductService importProductService;

    @GetMapping("/getall")
    public ResponseEntity<List<ImportProductDto>> getAllImportProducts() {
        List<ImportProductDto> importProducts = importProductService.findAllImportProducts();
        return new ResponseEntity<>(importProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportProductDto> getImportProductById(@PathVariable Long id) {
        ImportProductDto importProduct = importProductService.findImportProductById(id);
        if (importProduct != null) {
            return new ResponseEntity<>(importProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ImportProductDto> addImportProduct(@RequestBody ImportProductDto importProductDto) {
        ImportProductDto newImportProduct = importProductService.saveImportProduct(importProductDto);
        return new ResponseEntity<>(newImportProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ImportProductDto> updateImportProduct(@PathVariable Long id, @RequestBody ImportProductDto importProductDto) {
        ImportProductDto existingImportProduct = importProductService.findImportProductById(id);
        if (existingImportProduct != null) {
            importProductDto.setId(id);
            ImportProductDto updatedImportProduct = importProductService.saveImportProduct(importProductDto);
            return new ResponseEntity<>(updatedImportProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<ImportProductDto> updateImportProductStatus(@PathVariable Long id, @RequestBody ImportProductDto importProductDto) {
        ImportProductDto existingImportProduct = importProductService.findImportProductById(id);
        if (existingImportProduct != null) {
            existingImportProduct.setStatus(importProductDto.getStatus());
            ImportProductDto updatedImportProduct = importProductService.saveImportProduct(existingImportProduct);
            return new ResponseEntity<>(updatedImportProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}