package dao;

import config.ConnectionDB;
import interfaces.ProductoNuevoInterface;
import java.sql.Connection;
import java.util.List;
import model.ModelProductoNuevo;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author josueemilian
 */
public class DaoProductoNuevo implements ProductoNuevoInterface {

    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public List<ModelProductoNuevo> listar() {
        ArrayList<ModelProductoNuevo> lstProductoNuevo = new ArrayList<>();
        try {
            strSQL = "SELECT * FROM PRODUCTO_COMBO";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelProductoNuevo productoNuevo = new ModelProductoNuevo();
                productoNuevo.setId(rs.getInt("ID_COMBO"));
                productoNuevo.setIdtipo(rs.getInt("ID_TIPO_COMBO"));
                productoNuevo.setDescripcion(rs.getString("DESCRIPCION"));
                productoNuevo.setPrecio(rs.getDouble("PRECIO"));
                productoNuevo.setFecha(rs.getString("FECHA_CREA"));
                productoNuevo.setEstado(rs.getBoolean("ESTADO"));
                productoNuevo.setImg(rs.getString("IMAGEN"));

                lstProductoNuevo.add(productoNuevo);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProductoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoProductoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstProductoNuevo;
    }

    @Override
    public boolean Update(ModelProductoNuevo monitorCocina) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertarProductoNuevo(ModelProductoNuevo productoNuevo) {
        boolean res = false;
        strSQL = "INSERT INTO PRODUCTO_COMBO (ID_COMBO, ID_TIPO_COMBO, DESCRIPCION, PRECIO, FECHA_CREA, ESTADO, IMAGEN) "
                + "VALUES ((SELECT ISNULL(MAX(ID_COMBO), 0) + 1 FROM PRODUCTO_COMBO), ?, ?, ?, GETDATE(), ?, ?)";

        try {
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, productoNuevo.getIdtipo());
            pst.setString(2, productoNuevo.getDescripcion());
            pst.setDouble(3, productoNuevo.getPrecio());
            pst.setBoolean(4, productoNuevo.isEstado());
            pst.setString(5, productoNuevo.getImg());


            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoProductoNuevo.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

}
