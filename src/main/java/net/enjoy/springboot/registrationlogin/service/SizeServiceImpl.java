package net.enjoy.springboot.registrationlogin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import net.enjoy.springboot.registrationlogin.dto.SizeDto;
import net.enjoy.springboot.registrationlogin.entity.Size;
import net.enjoy.springboot.registrationlogin.repository.SizeRepository;

import org.springframework.stereotype.Service;
@Service
public class SizeServiceImpl implements SizeService {
     @Autowired
    private  SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override   
    public List<SizeDto> findAllSize() {
        List<Size> sizes = sizeRepository.findAll();
        // chi co category co status = 1 moi duoc hien thi
        sizes = sizes.stream().filter(size -> size.getStatus() == 1).collect(Collectors.toList());
        return sizes.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private SizeDto convertEntityToDto(Size size) {
        return new SizeDto(size.getId(), size.getSizeName(), size.getStatus());
    }

    @Override
    public Size findById(Long id) {
        System.out.println("_____ TOI DA CHAY DEN DAY ****** service" + id);

        return sizeRepository.findById(id).orElse(null);
        // TODO Auto-generated method stub
    }

    @Override
    public SizeDto saveSizeAPI(SizeDto sizeDto) {
        Size size = new Size();
        size.setSizeName(sizeDto.getSizeName());
        size.setStatus(sizeDto.getStatus());
        sizeRepository.save(size);
        return sizeDto;
    }
    @Override
    public SizeDto findSizeDtoById(Long sizeId) {
        Size size = sizeRepository.findById(sizeId).orElse(null);
        return new SizeDto(size.getId(), size.getSizeName(), size.getStatus());
    }

    @Override
    public Size updateSizeAPI(Size size) {
        sizeRepository.save(size);
        return size;
    }
}
