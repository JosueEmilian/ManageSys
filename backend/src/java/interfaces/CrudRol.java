package interfaces;

import java.util.List;
import model.ModelRol;

/**
 *
 * @author josueemilian
 */
public interface CrudRol {

    List<ModelRol> listar();
    public ModelRol list(int id);
    public boolean Register(ModelRol rol);
    public boolean Update(ModelRol rol);
    public boolean Delete(ModelRol rol);
    public List<ModelRol> Search(String rol);

}
