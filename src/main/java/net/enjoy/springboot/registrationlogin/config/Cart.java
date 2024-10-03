package net.enjoy.springboot.registrationlogin.config;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItemDto> items = new ArrayList<>();

    public List<CartItemDto> getItems() {
        return items;
    }

    public void addItem(CartItemDto item) {
        boolean isExist = false;
        for (CartItemDto cartItemDto : items) {
            if (cartItemDto.getIdDetail() == item.getIdDetail()) {
                cartItemDto.setQuantity(cartItemDto.getQuantity() + item.getQuantity());
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            items.add(item);
        }
    }

    public void clear() {
        items.clear();
    }
}