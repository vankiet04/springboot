package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ImportProductDto;
import net.enjoy.springboot.registrationlogin.entity.ImportProduct;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import java.util.List;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDto;
import org.springframework.data.domain.Page;
import net.enjoy.springboot.registrationlogin.dto.ImportDetailDtos;

public interface ImportProductService {
    List<ImportProductDto> findAllImportProducts();
    ImportProductDto findImportProductById(Long id);
    ImportProductDto saveImportProduct(ImportProductDto importProductDto);
    ImportProduct updateImportProduct(ImportProduct importProduct);
    void saveImportDetail(ImportDetailDto importDetail);
    void updateSoLuong(ImportDetailDto importDetail);
    List<ImportProductDto> findAllImportByPage(int page, int size);
    Page<ImportProductDto> findAllImportWithPage(int page, int size);
    List<ImportDetailDtos> findImportDetailsByImportId(Long importId);
    void updateGia(ImportDetailDto importDetail);
}