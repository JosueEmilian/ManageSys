package interfaces;

import java.util.List;
import model.ModelPermiso;

/**
 *
 * @author josueemilian
 */
public interface CrudPermiso {
    List<ModelPermiso> listar();
    public ModelPermiso list(int id);
    public boolean Register(ModelPermiso permiso);
    public boolean Update(ModelPermiso permiso);
    public boolean Delete(ModelPermiso permiso);
    public List<ModelPermiso> Search(String permiso);
}
