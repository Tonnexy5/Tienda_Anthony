/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendaa.dao;

import com.tiendaa.domain.Articulo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Lamec
 */
public interface ArticuloDao extends CrudRepository<Articulo, Long>{
    
}
