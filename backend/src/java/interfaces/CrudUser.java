package interfaces;

import java.util.List;
import model.ModelUser;

/**
 *
 * @author josueemilian
 */
public interface CrudUser {
    public List<ModelUser> listar();
    public ModelUser list(int id);
    public boolean Register(ModelUser usuario);
    public boolean Update(ModelUser usuario);
    public boolean Delete(ModelUser usuario);
    public List<ModelUser> Search(String usuario);
}
