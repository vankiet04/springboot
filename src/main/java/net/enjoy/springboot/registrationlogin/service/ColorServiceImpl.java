package net.enjoy.springboot.registrationlogin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import net.enjoy.springboot.registrationlogin.dto.ColorDto;
import net.enjoy.springboot.registrationlogin.entity.Color;
import net.enjoy.springboot.registrationlogin.repository.ColorRepository;

import org.springframework.stereotype.Service;
@Service
public class ColorServiceImpl implements ColorService {
    
     @Autowired
    private  ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override   
    public List<ColorDto> findAllColor() {
        List<Color> colors = colorRepository.findAll();
        // chi co category co status = 1 moi duoc hien thi
        colors = colors.stream().filter(color -> color.getStatus() == 1).collect(Collectors.toList());
        return colors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private ColorDto convertEntityToDto(Color color) {
        return new ColorDto(color.getId(), color.getColorName(), color.getStatus());
    }

    @Override
    public Color findById(Long id) {
        System.out.println("_____ TOI DA CHAY DEN DAY ****** service" + id);

        return colorRepository.findById(id).orElse(null);
        // TODO Auto-generated method stub
    }

    @Override
    public ColorDto saveColorAPI(ColorDto colorDto) {
        Color color = new Color();
        color.setColorName(colorDto.getColorName());
        color.setStatus(colorDto.getStatus());
        colorRepository.save(color);
        return colorDto;
    }
    @Override
    public ColorDto findColorDtoById(Long colorId) {
        Color color = colorRepository.findById(colorId).orElse(null);
        return new ColorDto(color.getId(), color.getColorName(), color.getStatus());
    }

    @Override
    public Color updateColorAPI(Color color) {
        colorRepository.save(color);
        return color;
    }
}
