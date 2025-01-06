package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import java.util.List;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDto;
public interface ImportProductService {
    List<ImportProductDto> findAllImportProducts();
    ImportProductDto findImportProductById(Long id);
    ImportProductDto saveImportProduct(ImportProductDto importProductDto);
    ImportProduct updateImportProduct(ImportProduct importProduct);
    void saveImportDetail(ImportDetailDto importDetail);
}