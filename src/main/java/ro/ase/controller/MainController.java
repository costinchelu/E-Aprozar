package ro.ase.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import ro.ase.entity.AuthorityType;
import ro.ase.entity.InfoUser;
import ro.ase.entity.Order;
import ro.ase.entity.OrderStatus;
import ro.ase.entity.Product;
import ro.ase.entity.Products;
import ro.ase.jdbc.InfoUserDAO;
import ro.ase.model.ChoiceCartList;
import ro.ase.service.impl.ChoiceCart;
import ro.ase.service.impl.ListOfDepartments;
import ro.ase.service.OrderServiceInterface;
import ro.ase.service.ProductServiceInterface;
import ro.ase.service.impl.ProductsXml;
import ro.ase.service.impl.ServiceCart;
import ro.ase.service.UserServiceInterface;

@Controller
@SessionAttributes("order")
public class MainController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    ListOfDepartments listOfDepartments;

    @Autowired
    ServiceCart serviceCart;

    @Autowired
    ChoiceCart choiceCart;

    @Autowired
    OrderServiceInterface orderService;

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ProductsXml productsXml;

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    /**
     * Pagina de start
     */
    @GetMapping({"/", "/welcome"})
    public String mainPage() {
        return "welcome";
    }

    @GetMapping("/setProduct")
    public String setProduct() {
        Products products = productsXml.getProductsFromXml();
        Logger logger = Logger.getLogger(MainController.class.getName());
        for (Product product : products.getProducts()) {
            Long id = productService.insert(product);
            String source_file = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + product.getIdProduct().toString() + "." + "jpg";
            Path path_source = Paths.get(source_file);
            String destination_file = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + id.toString() + "." + "jpg";
            Path path_destination = Paths.get(destination_file);
            if (Files.exists(path_source)) {
                try {
                    Files.copy(path_source, path_destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, null, e);
                }
            }
        }
        return "redirect:/welcome";
    }

    @GetMapping("/setAdmin")
    public String setAdmin() {
        if (userService.userAlreadyExist("2718@i.ua")) {
            return "redirect:/welcome";
        }
        InfoUser infoUser = new InfoUser();
        infoUser.setName("Dmitriy");
        infoUser.setEmail("2718@i.ua");
        infoUser.setPassword(new BCryptPasswordEncoder().encode("12345678"));
        infoUser.setEnabled(true);
        infoUser.setAuthority(AuthorityType.ADMIN);
        new InfoUserDAO().save(infoUser);
        return "redirect:/welcome";
    }

    /**
     * vizualizare produse
     */
    @GetMapping("/buy/{department}")
    public String buyProduct(@PathVariable("department") String department, @RequestParam(name = "page", defaultValue = "1", required = false) String page, Model model) {
        // conversie string -> int
        int pageNow = (int) serviceCart.getId(page);
        if (pageNow == -1) return "redirect:/welcome";
        for (String dep : listOfDepartments.getDepartments()) {
            if (department.equalsIgnoreCase(dep)) {
                List<Product> products = productService.findAllByDepartment(department);
                products.sort((Product prod1, Product prod2) -> prod1.getName().compareToIgnoreCase(prod2.getName()));
                // 8 produse pe pagina
                int productOnPage = 8;
                // nr pagini (cu max 8 produse/pagina)
                int pages = getPages(products.size(), productOnPage);
                // daca pagina curenta <= 0 atunci pagina curenta = 1
                if (pageNow <= 0) pageNow = 1;
                // pagina curenta > pagina max atunci pagina curenta = pagina max
                if (pageNow > pages) pageNow = pages;
                model.addAttribute("products", productList(products, pageNow, productOnPage));
                model.addAttribute("department", department);
                model.addAttribute("pages", pages);
                model.addAttribute("page", pageNow);
                return "buyPage";
            }
        }
        return "redirect:/welcome";
    }

    /**
     * detalii despre produs
     */
    @GetMapping("/buy/details/{id}")
    public String buyDetailsProduct(@PathVariable("id") String id, Model model) {
        Product product = getProduct(serviceCart.getId(id));
        if (product == null) {
            return "redirect:/welcome";
        }
        model.addAttribute("product", product);
        return "detailsProduct";
    }

    /**
     * formular pentru checkout
     */
    @GetMapping("/buy/checkout")
    public String checkout() {
        return "checkout";
    }

    /**
     * checkout
     */
    @PostMapping("/buy/complete")
    public String complete(@ModelAttribute("order") @Valid Order order, BindingResult result, HttpServletRequest request, Principal principal, Model model) {

        if (result.hasErrors()) {
            return "checkout";
        }

        // daca utilizatorul nu este inregistrat
        if (order.getIdOrder() == null) {
            model.addAttribute("order", new Order());
            return "redirect:/welcome";
        }

        // daca utilizatorul este inregistrat
        order.setPaidDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setStatus(OrderStatus.FINALIZATA.name());
        order.setPaid(true);

        // finalizeaza comanda
        orderService.update(order);

        InfoUser user = userService.findByEmail(principal.getName());

        // comandă goală
        Order orderNew = new Order();
        choiceCart.createNewOrder(orderNew, user);

        model.addAttribute("order", orderNew);
        request.getSession().setAttribute("currentUser", user);

        return "redirect:/welcome";
    }

    /**
     * Comenzi memorate
     */
    @GetMapping("/user/choiceCart")
    public String choiceCart(@SessionAttribute("currentUser") InfoUser user, Model model) {
        List<Order> orderUserNew = orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NOUA.name());
        // șterge comanda curentă
        orderUserNew.remove(orderUserNew.size() - 1);
        model.addAttribute("orderUserNew", orderUserNew);
        // adaugă lista de comenzi memorate
        model.addAttribute("choiceCartList", new ChoiceCartList());
        return "choiceCart";
    }

    /**
     * adaugă o comanda memorata la comanda curentă
     */
    @PostMapping("/user/mixCart")
    public String mixCart(@ModelAttribute("choiceCartList") ChoiceCartList choiceCartList, HttpServletRequest request, Principal principal, Model model) {
        if (choiceCartList.getOrderedDate().isEmpty()) {
            return "redirect:/welcome";
        }
        InfoUser user = userService.findByEmail(principal.getName());
        List<Order> orderUserNew = orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NOUA.name());
        Order orderUserNewLast = orderUserNew.get(orderUserNew.size() - 1);
        orderUserNew.remove(orderUserNewLast);
        for (Timestamp orderedDate : choiceCartList.getOrderedDate()) {
            for (Order orderUser : orderUserNew) {
                if (orderUser.getOrderedDate().equals(orderedDate)) {
                    orderService.delete(orderUserNewLast.getIdOrder());
                    orderUserNewLast = choiceCart.mixOrder(orderUserNewLast, orderUser);
                    orderService.update(orderUserNewLast);
                    break;
                }
            }
        }
        model.addAttribute("order", orderUserNewLast);
        request.getSession().setAttribute("currentUser", userService.findByEmail(principal.getName()));

        return "redirect:/welcome";
    }

    /**
     * afișare coș de cumpărături
     */
    @GetMapping("/buy/cart")
    public String viewCart() {
        return "cart";
    }

    /**
     * adaugă produs la comandă
     */
    @GetMapping("/buy/addToCart")
    public String addToCart(@RequestParam(name = "idProduct") String id, HttpServletRequest request) {
        Product product = getProduct(serviceCart.getId(id));
        if (product == null) {
            return "redirect:/welcome";
        }
        serviceCart.setItemsToCart(product, request);
        return "redirect:/buy/cart";
    }

    /**
     * modifică cantitatea
     */
    @GetMapping("/buy/quantity/{change}")
    public String minusItem(@PathVariable("change") String change, @RequestParam(name = "idProduct") String id, HttpServletRequest request) {
        Product product = getProduct(serviceCart.getId(id));
        if (product == null) {
            return "redirect:/welcome";
        }
        serviceCart.changeQuantityOfItems(product, request, change.equals("plus") ? 1 : change.equals("minus") ? -1 : 0);
        return "redirect:/buy/cart";
    }

    /**
     *  găsește produs după id
     */
    private Product getProduct(long id) {
        Product product = null;
        if (id > 0) {
            product = productService.find(id);
        }
        return product;
    }

    /**
     *  adună produsele de pe o pagină (max. 8)
     */
    private List<Product> productList(List<Product> products, int page, int productOnPage) {
        int pages = getPages(products.size(), productOnPage);
        if (pages == 1 || pages < page && products.size() <= productOnPage) {
            return products;
        }
        // pentru ultima pagină
        else if (pages == page || pages < page) {
            return products.subList(productOnPage * (pages - 1), products.size());
        }
        return products.subList(productOnPage * (page - 1), productOnPage * page);
    }

    /**
     * calculeaza numărul necesar de pagini
     */
    private int getPages(int size, int productOnPage) {
        return (int) Math.ceil(size / (double) productOnPage);
    }
}
