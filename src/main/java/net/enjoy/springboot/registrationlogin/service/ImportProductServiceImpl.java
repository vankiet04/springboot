package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;
import net.enjoy.springboot.registrationlogin.repository.ImportDetailRepository;
import net.enjoy.springboot.registrationlogin.repository.ImportProductRepository;
import net.enjoy.springboot.registrationlogin.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import java.util.List;
import java.util.stream.Collectors;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDto;
@Service
public class ImportProductServiceImpl implements ImportProductService {

    @Autowired
    private ImportProductRepository importProductRepository;

    @Autowired
    private ImportDetailRepository importDetailRepository;

    @Autowired
    private ProductDetailService productDetailService;

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
        ImportProduct importProduct = convertDtoToEntity(importProductDto);
        importProduct = importProductRepository.save(importProduct);
        return convertEntityToDto(importProduct);
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
        // debug importProductD
        System.out.println("importProductDto: ");
        ImportProduct importProduct = new ImportProduct();
        importProduct.setImportDate(importProductDto.getImportDate());
        importProduct.setTotalAmount(importProductDto.getTotalAmount());
        importProduct.setStatus(importProductDto.getStatus());
        importProduct.setSupplier(supplierService.findById(importProductDto.getSupplierId()));
        importProduct.setEmployee(employeeService.findById(importProductDto.getEmployeeId()));
        System.out.println(employeeService.findById(importProductDto.getEmployeeId()));
        return importProduct;
    }

    @Override
    public void saveImportDetail(ImportDetailDto importDetail) {
        ImportDetail importDetailEntity = new ImportDetail();
        importDetailEntity.setQuantity(importDetail.getQuantity());
        importDetailEntity.setProductDetail(productDetailService.getProductDetailEntity(importDetail.getProductDetailId()));
        importDetailEntity.setImportProduct(importProductRepository.findById(importDetail.getImportId()).get());
        importDetailRepository.save(importDetailEntity);
        
    }

    ImportDetail convertDtoToEntity(ImportDetailDto importDetailDto) {
        ImportDetail importDetail = new ImportDetail();
        importDetail.setQuantity(importDetailDto.getQuantity());
        importDetail.setProductDetail(productDetailService.getProductDetailEntity(importDetailDto.getProductDetailId()));
        importDetail.setImportProduct(importProductRepository.findById(importDetailDto.getImportId()).get());
        return importDetail;
    }

}