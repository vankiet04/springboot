package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;
import net.enjoy.springboot.registrationlogin.repository.ImportDetailRepository;
import net.enjoy.springboot.registrationlogin.repository.ImportProductRepository;
import net.enjoy.springboot.registrationlogin.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import java.util.List;
import java.util.stream.Collectors;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page; 
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDtos;
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

    @Override
    public Page<ImportProductDto> findAllImportWithPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ImportProduct> importProducts = importProductRepository.findAll(pageable);
        return importProducts.map(this::convertEntityToDto);
    }


    private ImportProductDto convertEntityToDto(ImportProduct importProduct) {
        ImportProductDto dto = new ImportProductDto();
        dto.setId(importProduct.getId());
        dto.setImportDate(importProduct.getImportDate());
        dto.setSupplierId(importProduct.getSupplier().getId());
        dto.setEmployeeId(importProduct.getEmployee().getId());
        dto.setTotalAmount(importProduct.getTotalAmount());
        dto.setStatus(importProduct.getStatus());
        return dto;
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
        importDetailEntity.setImportPrice(importDetail.getImportPrice());
        importDetailEntity.setExportPrice(importDetail.getExportPrice());
        importDetailRepository.save(importDetailEntity);
        
    }

    ImportDetail convertDtoToEntity(ImportDetailDto importDetailDto) {
        ImportDetail importDetail = new ImportDetail();
        importDetail.setQuantity(importDetailDto.getQuantity());
        importDetail.setProductDetail(productDetailService.getProductDetailEntity(importDetailDto.getProductDetailId()));
        importDetail.setImportProduct(importProductRepository.findById(importDetailDto.getImportId()).get());
        return importDetail;
    }

    @Override   
    public void updateSoLuong(ImportDetailDto importDetail) {
        ProductDetailDto productDetailDto = productDetailService.getProductDetail(importDetail.getProductDetailId());
        productDetailDto.setQuantity(productDetailDto.getQuantity() + importDetail.getQuantity());
        productDetailService.updateSoLuong(productDetailDto);
    }

    @Override
    public List<ImportProductDto> findAllImportByPage(int page, int size) {
        List<ImportProduct> importProducts = importProductRepository.findAll();
        return importProducts.stream()
                .skip((page - 1) * size)
                .limit(size)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImportDetailDtos> findImportDetailsByImportId(Long importId) {
        // fetch all then filter
        List<ImportDetail> importDetails = importDetailRepository.findAll();

        return importDetails.stream()
                .filter(importDetail -> importDetail.getImportProduct().getId().equals(importId))
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ImportDetailDtos convertEntityToDto(ImportDetail importDetail) {
        ImportDetailDtos importDetailDtos = new ImportDetailDtos();
        importDetailDtos.setId(importDetail.getId());
        importDetailDtos.setImportId(importDetail.getImportProduct().getId());
        importDetailDtos.setTensanpham(importDetail.getProductDetail().getProduct().getName());
        importDetailDtos.setQuantity(importDetail.getQuantity());
        importDetailDtos.setImportPrice(importDetail.getImportPrice());
        return importDetailDtos;
    }

    @Override
    public void updateGia(ImportDetailDto importDetail) {
        ProductDetailDto productDetailDto = productDetailService.getProductDetail(importDetail.getProductDetailId());
        System.out.println("chua vao update gia");
        if (productDetailDto.getPrice() < importDetail.getExportPrice()) {
            System.out.println("update gia");
            productDetailDto.setPrice(importDetail.getExportPrice());
            productDetailService.updateGia(productDetailDto);
        }
    }
}
