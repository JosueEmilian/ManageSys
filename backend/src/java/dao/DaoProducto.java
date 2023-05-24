/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConnectionDB;
import interfaces.ProductoInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModeloProducto;

/**
 *
 * @author abner
 */
public class DaoProducto implements ProductoInterface {
    ConnectionDB conexion = new ConnectionDB();
    ModeloProducto prod = new ModeloProducto();
    String strSQL = "";
    ResultSet rs = null;

    @Override
    public List<ModeloProducto> productos() {
        ArrayList<ModeloProducto> productos = new ArrayList<>();
        try {
            strSQL = "select * from producto_combo where estado = 1 ";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModeloProducto producto = new ModeloProducto(); 
                producto.setIdCombo(rs.getInt("ID_COMBO"));
                producto.setIdTipoCombo(rs.getInt("ID_TIPO_COMBO"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setPrecio(rs.getFloat("PRECIO"));
                producto.setFechaCreacion(rs.getString("FECHA_CREA"));
                producto.setEstado(rs.getInt("ESTADO"));
                producto.setImagen(rs.getString("IMAGEN"));
                productos.add(producto);
            }
            
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    @Override
    public ModeloProducto infoProducto(int id) {
        try {
            strSQL = "SELECT * \n"
                    + "FROM PRODUCTO_COMBO \n"
                    + "WHERE id_COMBO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                prod.setIdCombo(rs.getInt("ID_COMBO"));
                prod.setIdTipoCombo(rs.getInt("ID_TIPO_COMBO"));
                prod.setDescripcion(rs.getString("DESCRIPCION"));
                prod.setPrecio(rs.getFloat("PRECIO"));
                prod.setFechaCreacion(rs.getString("FECHA_CREA"));
                prod.setEstado(rs.getInt("ESTADO"));
                prod.setImagen(rs.getString("IMAGEN"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }
    
    
    public void gaurdarPrueba(String sql){
        try {
            File file = new File("log.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sql);
            bw.close();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean insertarProducto(ModeloProducto producto) {
        boolean res = false;
        strSQL = "INSERT INTO PRODUCTO_COMBO (ID_COMBO, ID_TIPO_COMBO, DESCRIPCION, PRECIO, FECHA_CREA, ESTADO, IMAGEN) "
                + "VALUES ((SELECT ISNULL(MAX(ID_COMBO), 0) + 1 FROM PRODUCTO_COMBO), ?, ?, ?, getdate(), ?, ?)";

        try {
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL); 
            pst.setInt(1, producto.getIdTipoCombo());
            pst.setString(2, producto.getDescripcion());
            pst.setFloat(3, producto.getPrecio());
            //pst.setString(5, producto.getFechaCreacion());
            pst.setInt(4, producto.getEstado()); 
            pst.setString(5, producto.getImagen());
            
            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            gaurdarPrueba("Error: "+ex);
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }
        return res;
    }

    @Override
    public boolean modififcarProducto(ModeloProducto producto) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conexion.open();

            // Se prepara la consulta SQL
                String sql = "UPDATE PRODUCTO_COMBO SET ID_TIPO_COMBO = ?, DESCRIPCION = ?, PRECIO = ?, ESTADO = ?, IMAGEN = ? WHERE ID_COMBO = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, producto.getIdTipoCombo());
            pst.setString(2, producto.getDescripcion());
            pst.setFloat(3, producto.getPrecio());
            pst.setInt(4, producto.getEstado());
            pst.setString(5, producto.getImagen());
            pst.setInt(6, producto.getIdCombo()); 

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
    public boolean eliminarProducto(int idProd) {
        try {
            strSQL = "UPDATE  producto_combo SET ESTADO = 0 WHERE ID_COMBO = ? ";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, idProd);
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (Exception e) {
            System.out.println("Error en Delete producto: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar producto (cerrando conexión): " + e.getMessage());
            }
        }    }
}
