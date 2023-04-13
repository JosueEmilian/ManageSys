package interfaces;

import java.util.List;
import model.ModelModulo;

/**
 *
 * @author josueemilian
 */
public interface CrudModulo {
    List<ModelModulo> listar();
    public ModelModulo list(int id);
    public boolean Register(ModelModulo modulo);
    public boolean Update(ModelModulo modulo);
    public boolean Delete(ModelModulo modulo);
    public List<ModelModulo> Search(String modulo);

}
