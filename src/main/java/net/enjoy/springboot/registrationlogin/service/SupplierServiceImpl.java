package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.SupplierDto;
import net.enjoy.springboot.registrationlogin.entity.Supplier;
import net.enjoy.springboot.registrationlogin.repository.SupplierRepository;
import net.enjoy.springboot.registrationlogin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierDto> findAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto findSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    @Override
    public SupplierDto saveSupplier(SupplierDto supplierDto) {
        Supplier supplier = convertDtoToEntity(supplierDto);
        supplier = supplierRepository.save(supplier);
        return convertEntityToDto(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    private SupplierDto convertEntityToDto(Supplier supplier) {
        return new SupplierDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getPhoneNumber(),
                supplier.getStatus()
        );
    }

    private Supplier convertDtoToEntity(SupplierDto supplierDto) {
        return new Supplier(
                supplierDto.getId(),
                supplierDto.getName(),
                supplierDto.getAddress(),
                supplierDto.getEmail(),
                supplierDto.getPhoneNumber(),
                supplierDto.getStatus());
    }
    
    @Override   
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }
}