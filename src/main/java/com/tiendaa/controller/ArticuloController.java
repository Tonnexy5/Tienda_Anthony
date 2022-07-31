package com.tiendaa.controller;

import com.tiendaa.domain.Articulo;
import com.tiendaa.service.ArticuloService;
import com.tiendaa.service.CategoriaService;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticuloController {
    
    
    @Autowired
    private ArticuloService articuloservice;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/articulo/listado")
    public String inicio(Model model){

        var articulos = articuloservice.getArticulos(false);
        
        model.addAttribute("articulos",articulos);
        return "articulo/listado";
    }
    @GetMapping("/articulo/nuevo")
    public String nuevoArticulo(Articulo articulo, Model model){
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias",categorias);
        return "articulo/modificar";
    }
    
    @PostMapping("/articulo/guardar")
    public String guardarArticulo(Articulo articulo){
        articuloservice.save(articulo);
        return "redirect:/articulo/listado";
    }
    
    @GetMapping("/articulo/modificar/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model){
        articulo=articuloservice.getArticulo(articulo);
        model.addAttribute("articulo",articulo);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias",categorias);
        return "articulo/modificar";
    }
    
    @GetMapping("/articulo/eliminar/{idArticulo}")
    public String eliminarArticulo(Articulo articulo){
        articuloservice.delete(articulo);
        return "redirect:/articulo/listado";
    }
}
