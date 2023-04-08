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
    public boolean Register(ModelRol usuario);
    public boolean Update(ModelRol usuario);
    public boolean Delete(ModelRol usuario);
    public List<ModelRol> Search(String usuario);

}
