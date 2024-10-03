package net.enjoy.springboot.registrationlogin.controller;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthController {
    private UserService userService;
    private Logger log;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page `reques`t


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    // để chuyển hướng đến trang chủ, lấy từ index.html thành map ur
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }


    @GetMapping("/order")
    public String order() {
        return "order";
    }

    @GetMapping("/order-detail")
    public String orderDetail() {
        return "order-detail";
    }

    @GetMapping("/product-detail")
    public String productDetail() {
        return "product-detail";
    }


    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Long id = printLoggedInUserId();
        if(id == null){
            return "redirect:/login";
        }
        System.out.println(id);
        User user = new User();
        user=userService.getUser(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable Long id,@Valid @ModelAttribute("user") User user,
                                @RequestParam("isPasswordChanged") boolean isPasswordChanged,
                                BindingResult result,Model model, Principal principal) {
  
        if (principal == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "profile";
        }

        User t = userService.updateUser(id, user, isPasswordChanged);
      
        model.addAttribute("user", t);
        System.out.println("ID USER ĐÃ ĐĂNG NHẬP: " +t.getEmail());
        return "redirect:/profile?success";


    }

    public Long printLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                User user = userService.findUserByEmail(username);
                if (user != null) {
                    System.out.println("ID USER ĐÃ ĐĂNG NHẬP: " +user.getId());
                    return user.getId();
                }
            }
        }
        return null;
    }


    // handler method to handle login reque
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}