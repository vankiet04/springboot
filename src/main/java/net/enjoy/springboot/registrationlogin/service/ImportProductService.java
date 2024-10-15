package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;

import java.util.List;

public interface ImportProductService {
    List<ImportProductDto> findAllImportProducts();
    ImportProductDto findImportProductById(Long id);
    ImportProductDto saveImportProduct(ImportProductDto importProductDto);
    ImportProduct updateImportProduct(ImportProduct importProduct);
}