package net.enjoy.springboot.registrationlogin.service;

import java.util.List;
import net.enjoy.springboot.registrationlogin.dto.ColorDto;
import net.enjoy.springboot.registrationlogin.entity.Color;

public interface ColorService {
     List<ColorDto> findAllColor();
     Color findById(Long id);

     ColorDto findColorDtoById(Long colorId);
     ColorDto saveColorAPI(ColorDto colorDto);
     Color updateColorAPI(Color color);
}
