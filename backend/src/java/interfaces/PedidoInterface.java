/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.ModeloPedido;

/**
 *
 * @author abner
 */
public interface PedidoInterface {
    public List<ModeloPedido> pedidos();
    public ModeloPedido infoPedido (int id);
    public boolean insertarPedido(ModeloPedido pedido);
    public boolean modificarPedido(ModeloPedido pedido);
    public boolean eliminarPedido(int idPedido);
}
