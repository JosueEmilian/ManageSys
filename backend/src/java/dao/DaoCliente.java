/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConnectionDB;
import interfaces.ClienteInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModeloCliente;

/**
 *
 * @author abner
 */
public class DaoCliente implements ClienteInterface {
    ConnectionDB conexion = new ConnectionDB();
    ModeloCliente usr = new ModeloCliente();
    String strSQL = "";
    ResultSet rs = null;
    
    
    @Override
    public List<ModeloCliente> clientes() {
        ArrayList<ModeloCliente> lsClientes = new ArrayList<>();
        try {
            strSQL = "select * from cliente where estado = 1 or estado is null ";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModeloCliente cliente = new ModeloCliente(); 
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setNit(rs.getString("NIT"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setNickname(rs.getString("NICKNAME"));
                cliente.setTelefono(rs.getString("TELEFONO"));
                lsClientes.add(cliente);
            }
            
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsClientes;
    }
 

    @Override
    public ModeloCliente infoCliente(int id) {
        try {
            strSQL = "SELECT * \n"
                    + "FROM USUARIO \n"
                    + "WHERE ID_USUARIO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                usr.setIdCliente(rs.getInt("ID_USUARIO"));
                usr.setNombre(rs.getString("NOMBRE"));
                usr.setNit(rs.getString("NIT"));
                usr.setNickname(rs.getString("NICKNAME"));
                usr.setDireccion(rs.getString("DIRECCION"));
                usr.setTelefono(rs.getString("TELEFONO"));
                usr.setRazonSocial(rs.getString("RAZON_SOCIAL")); 
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usr;
    }

    @Override
    public boolean insertarCliente(ModeloCliente cliente) {
        boolean res = false;
        strSQL = "INSERT INTO cliente (ID_CLIENTE, NOMBRE, NIT, RAZON_SOCIAL, NICKNAME, DIRECCION, TELEFONO, ESTADO) "
                + "VALUES ((SELECT ISNULL(MAX(ID_CLIENTE), 0) + 1 FROM CLIENTE), ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getNit());
            pst.setString(3, cliente.getRazonSocial());
            pst.setString(4, cliente.getNickname());
            pst.setString(5, cliente.getDireccion());
            pst.setString(6, cliente.getTelefono()); 
            pst.setInt(7, cliente.getEstado());
            
            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    @Override
    public boolean modificarCliente(ModeloCliente cliente) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conexion.open();

            // Se prepara la consulta SQL
                String sql = "UPDATE CLIENTE SET NOMBRE = ?, NIT = ?, NICKNAME = ?, DIRECCION = ?, RAZON_SOCIAL = ?, TELEFONO = ? WHERE ID_USUARIO = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getNit());
            pst.setString(3, cliente.getNickname());
            pst.setString(4, cliente.getDireccion());
            pst.setString(5, cliente.getRazonSocial());
            pst.setString(6, cliente.getTelefono());
            pst.setInt(7, cliente.getIdCliente()); 

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;

    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        try {
            strSQL = "UPDATE  CLIENTE SET ESTADO = 0 WHERE ID_USUARIO = ? ";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, idCliente);
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (Exception e) {
            System.out.println("Error en Delete usuario: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar usuario (cerrando conexión): " + e.getMessage());
            }
        }
    }
}
