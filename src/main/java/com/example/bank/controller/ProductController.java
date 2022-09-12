package com.example.bank.controller;

import com.example.bank.model.Product;
import com.example.bank.model.ProductForm;
import com.example.bank.service.IProductService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    Environment env;

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Product> products = (List<Product>) productService.findAll();
        modelAndView.addObject("products", products);
        modelAndView.addObject("message", "Thanh cong");
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView createProduct(@ModelAttribute ProductForm product){

        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product1 = new Product(product.getName(), product.getDescription(), fileName);
        productService.save(product1);
        return new RedirectView("");
    }
}
