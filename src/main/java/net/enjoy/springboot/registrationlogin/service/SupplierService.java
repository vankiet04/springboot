package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.SupplierDto;
import net.enjoy.springboot.registrationlogin.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> findAllSuppliers();
    Supplier findById(Long id);
    SupplierDto findSupplierById(Long id);
    
    SupplierDto saveSupplier(SupplierDto supplierDto);
    Supplier updateSupplier(Supplier supplier);
}