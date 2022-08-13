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


@Controller
@Slf4j
public class ClienteController {
    
    
    @Autowired
    private ClienteService clienteservice;
    
    @GetMapping("/cliente/busqueda")
    public String busqueda(Cliente cliente){
        return "cliente/buscar";
    }
    @PostMapping("/cliente/buscar")
    public String buscarCliente(Cliente cliente, Model model){
        cliente = clienteservice.getByApellidos(cliente);
        if(cliente!=null){
            model.addAttribute("cliente",cliente);
            return "/cliente/modificar";
        }else{
            return "redirect:/cliente/buscar";
        }
    }
    
    
    @GetMapping("/cliente/listado")
    public String inicio(Model model){
        var clientes = clienteservice.getClientes();
        var limiteTotal=0;
        for(var c:clientes){
        limiteTotal+=c.getCredito().getLimite();
        }
        model.addAttribute("limiteTotal",limiteTotal);
        model.addAttribute("totalClientes",clientes.size());
        model.addAttribute("clientes",clientes);
        return "cliente/listado";
    }
    @GetMapping("/cliente/nuevo")
    public String nuevoCliente(Cliente cliente){
        return "cliente/modificar";
    }
    
    @PostMapping("/cliente/guardar")
    public String guardarCliente(Cliente cliente){
        clienteservice.save(cliente);
        return "redirect:/cliente/listado";
    }
    
    @GetMapping("/cliente/modificar/{id_cliente}")
    public String modificarCliente(Cliente cliente, Model model){
        cliente=clienteservice.getCliente(cliente);
        model.addAttribute("cliente",cliente);
        return "cliente/modificar";
    }
    
    @GetMapping("/cliente/eliminar/{id_cliente}")
    public String eliminarCliente(Cliente cliente){
        clienteservice.delete(cliente);
        return "redirect:/cliente/listado";
    }
}
