package ro.ase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ro.ase.entity.InfoUser;
import ro.ase.entity.Order;
import ro.ase.entity.OrderStatus;
import ro.ase.service.impl.ChoiceCart;
import ro.ase.service.LineItemServiceInterface;
import ro.ase.service.OrderServiceInterface;
import ro.ase.service.UserServiceInterface;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    LineItemServiceInterface lineItemService;

    @Autowired
    OrderServiceInterface orderService;

    @Autowired
    ChoiceCart choiceCart;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginFailed")
    public String loginFailed(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, SessionStatus sessionStatus) {
        Order order = (Order) request.getSession().getAttribute("order");
        if (order.getLineItem().isEmpty()) {
            orderService.delete(order.getIdOrder());
        }
        request.getSession().setAttribute("order", new Order());
        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @PostMapping("/postLogin")
    public String postLogin(HttpServletRequest request, Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        InfoUser infoUser = userService.findByEmail(principal.getUsername());
        Order order = (Order) request.getSession().getAttribute("order");
        List<Order> orderUserNew = orderService.findAllByIdUserStatus(infoUser.getIdUser(), OrderStatus.NOUA.name());
        if (!orderUserNew.isEmpty()) {
            Order orderUserNewLast = orderUserNew.get(orderUserNew.size() - 1);
            if (orderUserNewLast.getLineItem().isEmpty()) {
                orderUserNew.remove(orderUserNewLast);
                infoUser.getOrders().remove(orderUserNewLast);
                orderService.delete(orderUserNewLast.getIdOrder());
            }
        }
        // dacă utilizatorul nu are comandă se adaugă la comandă nouă
        if (orderUserNew.isEmpty()) {
            choiceCart.createNewOrder(order, infoUser);
            model.addAttribute("currentUser", infoUser);
            return "redirect:/welcome";
        }

        if (order.getLineItem().isEmpty() && orderUserNew.size() == 1) {
            request.getSession().setAttribute("order", orderUserNew.get(0));
            model.addAttribute("currentUser", infoUser);
            return "redirect:/welcome";
        }

        choiceCart.createNewOrder(order, infoUser);
        model.addAttribute("currentUser", infoUser);
        return "redirect:/user/choiceCart";
    }
}