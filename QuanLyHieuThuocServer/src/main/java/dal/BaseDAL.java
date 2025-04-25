/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public interface BaseDAL <T, ID>{
    boolean insert(T t);
    boolean update(T t);
    Optional<T> findById(ID id);
    List<T> findAll();
}
