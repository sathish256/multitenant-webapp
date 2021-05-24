package com.multitenant.saml.controller;

import com.multitenant.saml.models.Products;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/auth")
    public String handleSamlAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return "redirect:/home";
        } else {
            return "/";
        }
    }

    @RequestMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getPrincipal());
        model.addAttribute("products", getProducts());
        return "home";
    }


    public List<Products> getProducts() {
        List<Products> products = new ArrayList<>();
        for (int i=1;i<=10;i++) {
            Products product = new Products("id"+i, "category"+i, "name"+i, Double.parseDouble(""+i*i),Arrays.asList("tag"+i), new Date());
            products.add(product);
        }

        return products;
    }

}
