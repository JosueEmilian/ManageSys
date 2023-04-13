package dao;

import config.ConnectionDB;
import interfaces.CrudModulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelModulo;

/**
 *
 * @author josueemilian
 */
public class DaoModulo implements CrudModulo {

    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;
    String strSQL = "";

    //LISTAR TODOS LOS MODULOS
    //Utilizando cáusala CTE, realiza búsqueda jerárquica de los modulos. 
    @Override
    public List<ModelModulo> listar() {
        ArrayList<ModelModulo> lstModulo = new ArrayList<>();
        try {
            strSQL = "WITH CTE_MODULOS AS (\n"
                    + "  SELECT ID_MODULO, NOMBRE, PATH, NIVEL, ID_MODULO_PADRE\n"
                    + "  FROM MODULO\n"
                    + "  WHERE ID_MODULO_PADRE IS NULL\n"
                    + "  UNION ALL\n"
                    + "  SELECT m.ID_MODULO, m.NOMBRE, m.PATH, m.NIVEL, m.ID_MODULO_PADRE\n"
                    + "  FROM MODULO m\n"
                    + "  INNER JOIN CTE_MODULOS c ON m.ID_MODULO_PADRE = c.ID_MODULO\n"
                    + ")\n"
                    + "SELECT ID_MODULO, NOMBRE, PATH, NIVEL, ID_MODULO_PADRE\n"
                    + "FROM CTE_MODULOS\n"
                    + "ORDER BY NIVEL, ID_MODULO_PADRE, ID_MODULO;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelModulo modulo = new ModelModulo();
                modulo.setId(rs.getInt("ID_MODULO"));
                modulo.setNombre(rs.getString("NOMBRE"));
                modulo.setPath(rs.getString("PATH"));
                modulo.setNivel(rs.getInt("NIVEL"));
                modulo.setIdModuloPadre(rs.getInt("ID_MODULO_PADRE"));
                lstModulo.add(modulo);
            }

            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstModulo;
    }

    //LISTAR MODULO INDIVIDUAL POR ID
    @Override
    public ModelModulo list(int id) {
        ModelModulo modulo = new ModelModulo();
        try {
            strSQL = "SELECT * FROM MODULO WHERE ID_MODULO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                modulo.setId(rs.getInt("ID_MODULO"));
                modulo.setNombre(rs.getString("NOMBRE"));
                modulo.setPath(rs.getString("PATH"));
                modulo.setNivel(rs.getInt("NIVEL"));
                modulo.setIdModuloPadre(rs.getInt("ID_MODULO_PADRE"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return modulo;
    }

    //REGISTRAR NUEVOS MODULOS
    @Override
    public boolean Register(ModelModulo modulo) {
        boolean res = false;
        try {
            strSQL = "INSERT INTO MODULO (ID_MODULO, NOMBRE, PATH, NIVEL, ID_MODULO_PADRE) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_MODULO), 0) + 1 FROM MODULO), ?, ?, ?, ?)";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, modulo.getNombre());
            pst.setString(2, modulo.getPath());
            pst.setInt(3, modulo.getNivel());
            Integer idModuloPadre = modulo.getIdModuloPadre();
            if (idModuloPadre != null) {
                pst.setInt(4, idModuloPadre);
            } else {
                pst.setNull(4, Types.INTEGER);
            }

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    //ACTUALIZR MODULOS
    @Override
    public boolean Update(ModelModulo modulo) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE MODULO SET NOMBRE = ?, PATH = ?, NIVEL = ?, ID_MODULO_PADRE = ? WHERE ID_MODULO = ?";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setString(1, modulo.getNombre());
            pst.setString(2, modulo.getPath());
            pst.setInt(3, modulo.getNivel());
            Integer idModuloPadre = modulo.getIdModuloPadre();
            if (idModuloPadre != null) {
                pst.setInt(4, idModuloPadre);
            } else {
                pst.setNull(4, Types.INTEGER);
            }
            pst.setInt(5, modulo.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    //ELIMINAR MODULOS
    @Override
    public boolean Delete(ModelModulo modulo) {
        try {
            strSQL = "DELETE MODULO WHERE ID_MODULO = ? ";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, modulo.getId());

            int result = pst.executeUpdate();
            return (result > 0);
        } catch (Exception e) {
            System.out.println("Error al eliminar el modulo: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar modulo (cerrando conexión): " + e.getMessage());
            }
        }
    }

    //BUSCAR MODULO POR NOMBRE O POR PATH
    @Override
    public List<ModelModulo> Search(String modulo) {
        List<ModelModulo> lst = new ArrayList<>();
        try {
            strSQL = "SELECT * FROM MODULO WHERE NOMBRE = ? OR PATH LIKE ?";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, modulo);
            pst.setString(2, modulo + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ModelModulo nModulo = new ModelModulo();
                nModulo.setId(rs.getInt("ID_MODULO"));
                nModulo.setNombre(rs.getString("NOMBRE"));
                nModulo.setPath(rs.getString("PATH"));
                nModulo.setNivel(rs.getInt("NIVEL"));
                nModulo.setIdModuloPadre(rs.getInt("ID_MODULO_PADRE"));
                lst.add(nModulo);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                Logger.getLogger(DaoModulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

}
