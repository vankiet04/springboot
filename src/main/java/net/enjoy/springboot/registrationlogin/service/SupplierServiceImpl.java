package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.SupplierDto;
import net.enjoy.springboot.registrationlogin.entity.Supplier;
import net.enjoy.springboot.registrationlogin.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierDto> findAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findSupplierz();
        return suppliers.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public SupplierDto findSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id);
        return convertEntityToDto(supplier);
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
                supplier.getStatus());
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
        return supplierRepository.findById(id);
    }

    @Override
    public List<SupplierDto> findAllSupplierWithPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Supplier> suppliers = supplierRepository.findAllByStatus(pageable);

        return suppliers.map(this::convertEntityToDto).stream().collect(Collectors.toList());
    }
}