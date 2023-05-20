/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.ModeloCliente;

/**
 *
 * @author abner
 */
public interface ClienteInterface {
    public List<ModeloCliente> clientes();
    public ModeloCliente infoCliente (int id);
    public boolean insertarCliente(ModeloCliente cliente);
    public boolean modificarCliente(ModeloCliente cliente);
    public boolean eliminarCliente(int idCliente);
}
