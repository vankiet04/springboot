package net.enjoy.springboot.registrationlogin.controller;
import net.enjoy.springboot.registrationlogin.dto.SupplierDto;
import net.enjoy.springboot.registrationlogin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/suppliers")
public class API_SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getall")
    public List<SupplierDto> getAllSuppliers() {
        return supplierService.findAllSuppliers();
        
    }

    @GetMapping("/getSupplierPage")
    public List<SupplierDto> getSupplierWithPage(@RequestParam(defaultValue = "1") int page) {
        return supplierService.findAllSupplierWithPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) {
        SupplierDto supplier = supplierService.findSupplierById(id);
        if (supplier != null) {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<SupplierDto> addSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto newSupplier = supplierService.saveSupplier(supplierDto);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        SupplierDto existingSupplier = supplierService.findSupplierById(id);
        if (existingSupplier != null) {
            supplierDto.setId(id);
            SupplierDto updatedSupplier = supplierService.saveSupplier(supplierDto);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<SupplierDto> updateSupplierStatus(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        SupplierDto existingSupplier = supplierService.findSupplierById(id);
        if (existingSupplier != null) {
            existingSupplier.setStatus(supplierDto.getStatus());
            SupplierDto updatedSupplier = supplierService.saveSupplier(existingSupplier);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}