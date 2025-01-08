package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import net.enjoy.springboot.registrationlogin.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDto;
import java.util.List;
import org.springframework.data.domain.Page;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDtos;
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

    @PostMapping("/importdetailandupdateproductdetail")
    public ResponseEntity<?> ImportDetail(@RequestBody List<ImportDetailDto> importDetailDtos) {
        for (ImportDetailDto importDetailDto : importDetailDtos) {
            importProductService.saveImportDetail(importDetailDto);
        }
        // update so luong
        for (ImportDetailDto importDetailDto : importDetailDtos) {
            importProductService.updateSoLuong(importDetailDto);
        }
        // update gia = max export cuar cac gia xuat
        for (ImportDetailDto importDetailDto : importDetailDtos) {
            importProductService.updateGia(importDetailDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addImportProduct(@RequestBody ImportProductDto importProductDto) {
        ImportProductDto newImportProduct = importProductService.saveImportProduct(importProductDto);
        return new ResponseEntity<>(newImportProduct.getId(), HttpStatus.CREATED);
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

    @GetMapping("/getAllImport")
    public ResponseEntity<Page<ImportProductDto>> getAllImportProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Page<ImportProductDto> importProducts = importProductService.findAllImportWithPage(page, size);
        return new ResponseEntity<>(importProducts, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<List<ImportDetailDto>> getImportDetails(@PathVariable Long id) {
        List<ImportDetailDtos> details = importProductService.findImportDetailsByImportId(id);
        // for detail system out
        for (ImportDetailDtos detail : details) {
            System.out.println(detail.getTensanpham());
        }
        return new ResponseEntity(details, HttpStatus.OK);
    }

}