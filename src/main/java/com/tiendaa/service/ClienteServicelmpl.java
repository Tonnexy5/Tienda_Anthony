/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendaa.service;

import com.tiendaa.dao.ClienteDao;
import com.tiendaa.domain.Cliente;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaa.dao.CreditoDao;
import com.tiendaa.domain.Credito;

/**
 *
 * @author Lamec
 */
@Service
public class ClienteServicelmpl implements ClienteService{
    
    //se crea en tiempo de ejecucion si aun no se ha creado...
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private CreditoDao creditoDao;
    
    @Override
    @Transactional(readOnly=true)
    public Cliente getByApellidos(Cliente cliente){
        return ((List<Cliente>) clienteDao.findByApellidos(cliente.getApellidos())).get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        Credito credito = cliente.getCredito();
        credito = creditoDao.save(credito);
        cliente.setCredito(credito);
        
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getId_cliente()).orElse(null);
    }
    
}
