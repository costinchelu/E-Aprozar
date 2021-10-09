package ro.ase.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ro.ase.entity.Product;
import ro.ase.model.UploadProduct;
import ro.ase.service.impl.ListOfDepartments;
import ro.ase.service.ProductServiceInterface;
import ro.ase.service.impl.ServiceCart;

@Controller
public class AdminController {

    Logger logger = Logger.getLogger(AdminController.class.getName());

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    ListOfDepartments listOfDepartments;

    @Autowired
    ServiceCart serviceCart;

    /**
     * adauga lista de categorii din department.xml
     */
    @ModelAttribute("listDepartments")
    private List<String> fromXml() {
        return listOfDepartments.getDepartments();
    }

    /**
     * gestiune produs
     */
    @GetMapping("/managementProduct")
    public String managementProduct() {
        return "managementProduct";
    }

    /**
     * adauga produs
     */
    @GetMapping("/managementProduct/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("uplProduct", new UploadProduct());
        return "addProduct";
    }

    /**
     * adauga produs
     */
    @PostMapping("/managementProduct/uploadProduct")
    public String uploadProduct(@ModelAttribute("uplProduct") @Valid UploadProduct uplProduct, BindingResult result) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        Product product = convertUploadProductInProduct(uplProduct);
        Long id = productService.insert(product);

        // adauga imaginile produselor
        if (uplProduct.getImage() != null && !uplProduct.getImage().getOriginalFilename().isEmpty()) {
            //String[] extension = uplProduct.getImage().getOriginalFilename().split("\\.");
            String path = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images";
            String fileName = id.toString() + "." + "jpg";//extension[extension.length-1];
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path, fileName)));
                outputStream.write(uplProduct.getImage().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return "redirect:/managementProduct";
    }

    /**
     * arata produse
     */
    @GetMapping("/managementProduct/showProduct/{department}")
    public String showProduct(@PathVariable("department") String department, Model model) {

        List<Product> products;
        List<String> listDepartmentsFromXml = fromXml();

        if (department.equalsIgnoreCase("all")) {
            products = productService.findAll();
            products.sort((Product prod1, Product prod2) -> prod1.getName().compareToIgnoreCase(prod2.getName()));
            model.addAttribute("products", products);
            model.addAttribute("department", department);
            return "allProduct";
        }
        // arata produsele de un singur tip
        for (String dep : listDepartmentsFromXml) {
            if (department.equalsIgnoreCase(dep)) {
                products = productService.findAllByDepartment(department);
                products.sort((Product prod1, Product prod2) -> prod1.getName().compareToIgnoreCase(prod2.getName()));
                model.addAttribute("products", products);
                model.addAttribute("department", department);
                return "allProduct";
            }
        }
        return "managementProduct";
    }

    /**
     * sterge produs
     */
    @GetMapping("/managementProduct/delete/{department}/{id}")
    public String deleteProduct(@PathVariable("department") String department, @PathVariable("id") String id) {
        //convert String to Long, if issue then to return -1L
        Long idProduct = serviceCart.getId(id);
        if (idProduct > 0) {
            productService.delete(idProduct);
            String file = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + idProduct.toString() + "." + "jpg";
            Path path = Paths.get(file);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return "redirect:/managementProduct/showProduct/" + department;
    }

    /**
     * update produs
     */
    @GetMapping("/managementProduct/update/{id}")
    public String updateProduct(@PathVariable("id") String id, Model model) {
        // conversie string -> long (sau -1L)
        Long idProduct = serviceCart.getId(id);
        if (idProduct > 0) {
            Product product = productService.find(idProduct);
            if (product == null) {
                return "managementProduct";
            }
            model.addAttribute("product", product);
            UploadProduct updProduct = convertProductInUploadProduct(product);
            model.addAttribute("updProduct", updProduct);
            return "updateProduct";
        }
        return "managementProduct";
    }

    @PostMapping("/managementProduct/update/updateProduct")
    public String updProduct(@ModelAttribute("updProduct") @Valid UploadProduct updProduct, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("product", productService.find(updProduct.getIdProduct()));
            return "updateProduct";
        }

        Product product = convertUploadProductInProduct(updProduct);
        productService.update(convertUploadProductInProduct(updProduct));
        // salvare imagine
        if (updProduct.getImage() != null && !updProduct.getImage().getOriginalFilename().isEmpty()) {
            String path = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images";
            String fileName = product.getIdProduct().toString() + "." + "jpg";
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path, fileName)));
                outputStream.write(updProduct.getImage().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return "redirect:/managementProduct/update/" + updProduct.getIdProduct().toString();
    }

    private Product convertUploadProductInProduct(UploadProduct uploadProduct) {
        Product product = new Product();
        product.setIdProduct(uploadProduct.getIdProduct());
        product.setDepartment(uploadProduct.getDepartment());
        product.setName(uploadProduct.getName());
        product.setPrice(uploadProduct.getPrice());
        product.setCurrency(uploadProduct.getCurrency());
        product.setAvailability(uploadProduct.getAvailability());
        product.setDescription(uploadProduct.getDescription());
        List<String> specifications = product.getSpecifications();
        if (uploadProduct.getSpecification1() != null) specifications.add(uploadProduct.getSpecification1());
        if (uploadProduct.getSpecification2() != null) specifications.add(uploadProduct.getSpecification2());
        if (uploadProduct.getSpecification3() != null) specifications.add(uploadProduct.getSpecification3());
        if (uploadProduct.getSpecification4() != null) specifications.add(uploadProduct.getSpecification4());
        if (uploadProduct.getSpecification5() != null) specifications.add(uploadProduct.getSpecification5());
        product.setSpecifications(specifications);
        return product;
    }

    private UploadProduct convertProductInUploadProduct(Product product) {
        UploadProduct uploadProduct = new UploadProduct();
        uploadProduct.setIdProduct(product.getIdProduct());
        uploadProduct.setDepartment(product.getDepartment());
        uploadProduct.setName(product.getName());
        uploadProduct.setPrice(product.getPrice());
        uploadProduct.setCurrency(product.getCurrency());
        uploadProduct.setAvailability(product.getAvailability());
        uploadProduct.setDescription(product.getDescription());
        if (product.getSpecifications() != null) {
            if (product.getSpecifications().size() >= 1) {
                uploadProduct.setSpecification1(product.getSpecifications().get(0));
            }
            if (product.getSpecifications().size() >= 2) {
                uploadProduct.setSpecification2(product.getSpecifications().get(1));
            }
            if (product.getSpecifications().size() >= 3) {
                uploadProduct.setSpecification3(product.getSpecifications().get(2));
            }
            if (product.getSpecifications().size() >= 4) {
                uploadProduct.setSpecification4(product.getSpecifications().get(3));
            }
            if (product.getSpecifications().size() == 5) {
                uploadProduct.setSpecification5(product.getSpecifications().get(4));
            }
        }
        String path = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + product.getIdProduct().toString() + ".jpg";
        Logger logger = Logger.getLogger(AdminController.class.getName());
        try {
            uploadProduct.setImage(new MockMultipartFile(product.getIdProduct().toString() + ".jpg", new FileInputStream(new File(path))));
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return uploadProduct;
    }
}
