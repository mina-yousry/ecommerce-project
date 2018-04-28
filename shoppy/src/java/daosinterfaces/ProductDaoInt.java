/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;

import dtos.ProductDto;
import java.util.HashMap;

/**
 *
 * @author Mina
 */
public interface ProductDaoInt {
    
    public boolean create(ProductDto product);

    public HashMap<Integer,ProductDto> retrieve(String name);

    public boolean update(ProductDto product);

    public boolean delete(ProductDto product);
    
}
