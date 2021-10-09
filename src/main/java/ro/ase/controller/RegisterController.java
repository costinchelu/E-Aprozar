package ro.ase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ro.ase.model.UserRegistration;
import ro.ase.service.UserServiceInterface;

@Controller
public class RegisterController {

    @Autowired
    UserServiceInterface userService;

    /**
     * formular înregistrare utilizator
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserRegistration());
        return "register";
    }

    /**
     * salvează utilizator
     */
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid UserRegistration user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.userAlreadyExist(user.getEmail())) {
            model.addAttribute("message", "User already exist!");
            return "register";
        } else {
            userService.save(user);
            return "redirect:/registered";
        }
    }

    /**
     * utilizator înregistrat
     */
    @GetMapping("/registered")
    public String registeredUser() {
        return "registered";
    }
}
