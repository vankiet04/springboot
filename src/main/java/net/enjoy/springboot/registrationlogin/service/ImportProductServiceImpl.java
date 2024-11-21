package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;
import net.enjoy.springboot.registrationlogin.repository.ImportProductRepository;
import net.enjoy.springboot.registrationlogin.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportProductServiceImpl implements ImportProductService {

    @Autowired
    private ImportProductRepository importProductRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SupplierService supplierService;

    @Override
    public List<ImportProductDto> findAllImportProducts() {
        return importProductRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }



    @Override
    public ImportProductDto saveImportProduct(ImportProductDto importProductDto) {
        // ImportProduct importProduct = convertDtoToEntity(importProductDto);
        // importProduct = importProductRepository.save(importProduct);
        // return convertEntityToDto(importProduct);
        return null;
    }

    @Override
    public ImportProductDto findImportProductById(Long id) {
        return null;
    }

    @Override
    public ImportProduct updateImportProduct(ImportProduct importProduct) {
        return null;
    }



    private ImportProductDto convertEntityToDto(ImportProduct importProduct) {
        return new ImportProductDto(
                importProduct.getId(),
                importProduct.getImportDate(),
                importProduct.getSupplier().getId(),
                importProduct.getEmployee().getId(),
                importProduct.getTotalAmount(),
                importProduct.getStatus());
    }

    private ImportProduct convertDtoToEntity(ImportProductDto importProductDto) {
        // ImportProduct importProduct = new ImportProduct();
        // importProduct.setId(importProductDto.getId());
        // importProduct.setImportDate(importProductDto.getImportDate());
        // importProduct.setTotalAmount(importProductDto.getTotalAmount());
        // importProduct.setStatus(importProductDto.getStatus());
        // // Assuming you have methods to fetch Supplier and Employee by their IDs
        // importProduct.setSupplier(supplierService.findById(importProductDto.getSupplierId()));
        // importProduct.setEmployee(employeeService.findById(importProductDto.getEmployeeId()));
        // return importProduct;
        return null;
    }

}