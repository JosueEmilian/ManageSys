/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.ModelProductoNuevo;

/**
 *
 * @author josueemilian
 */
public interface ProductoNuevoInterface {

    List<ModelProductoNuevo> listar();

    public boolean Update(ModelProductoNuevo monitorCocina);

    public boolean insertarProductoNuevo(ModelProductoNuevo productoNuevo);

}
