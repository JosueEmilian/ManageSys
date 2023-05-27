/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.ModeloProducto;

/**
 *
 * @author abner
 */
public interface ProductoInterface {
    public List<ModeloProducto> productos();
    public ModeloProducto infoProducto (int id);
    public boolean insertarProducto(ModeloProducto producto);
    public boolean modififcarProducto(ModeloProducto producto);
    public boolean eliminarProducto(int idProd);
}
