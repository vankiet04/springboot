package net.enjoy.springboot.registrationlogin.service;

import java.util.List;

import net.enjoy.springboot.registrationlogin.dto.SizeDto;
import net.enjoy.springboot.registrationlogin.entity.Size;

public interface SizeService {
      List<SizeDto> findAllSize();
     Size findById(Long id);

     SizeDto findSizeDtoById(Long sizeId);
     SizeDto saveSizeAPI(SizeDto sizeDto);
     Size updateSizeAPI(Size size);
}
