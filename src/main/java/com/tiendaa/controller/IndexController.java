package com.tiendaa.controller;

import com.tiendaa.domain.Cliente;
import com.tiendaa.service.ClienteService;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tiendaa.dao.CreditoDao;
import com.tiendaa.service.ArticuloService;

@Controller
@Slf4j
public class IndexController {
    
        @Autowired
    private ArticuloService articuloservice;
        
        
    @GetMapping("/")
    public String inicio(Model model){
        
        var articulos = articuloservice.getArticulos(true);
        model.addAttribute("articulos",articulos);    
        return "index";
    }

}
