package ro.ase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import ro.ase.entity.InfoUser;
import ro.ase.entity.OrderStatus;
import ro.ase.model.UserUpdate;
import ro.ase.service.OrderServiceInterface;
import ro.ase.service.UserServiceInterface;

@Controller
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    OrderServiceInterface orderService;

    /**
     * Toate comenzile
     */
    @GetMapping("/user/orderList")
    public String orderInfo() {
        return "orderList";
    }

    /**
     * Meniu utilizator
     */
    @GetMapping("/user/accountInfo")
    public String accountInfo(@SessionAttribute("currentUser") InfoUser user, Model model) {
        model.addAttribute("orderNewSize", orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NOUA.name()).size() - 1);
        return "accountinfo";
    }

    /**
     * Informatii utilizator
     */
    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }

    /**
     * Actualizare utilizator - FORMULAR
     */
    @GetMapping("/user/profileUpdate")
    public String profileUpdate(Model model) {
        model.addAttribute("userUpdate", new UserUpdate());
        return "profileUpdate";
    }

    /**
     * Actualizare utilizator
     */
    @PostMapping("/user/updateUser")
    public String saveUser(@ModelAttribute("userUpdate") @Valid UserUpdate user, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "profileUpdate";
        }
        InfoUser userInfo = (InfoUser) request.getSession().getAttribute("currentUser");
        userInfo.setSurname(user.getSurname());
        userInfo.setName(user.getName());
        userInfo.setDetails(user.getDetails());
        userInfo.setPhone(user.getPhone());
        userInfo.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.update(userInfo);
        return "redirect:/user/profile";
    }
}
