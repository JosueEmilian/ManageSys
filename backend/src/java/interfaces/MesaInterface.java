package interfaces;

import java.util.List;
import model.ModelMesa;

/**
 *
 * @author josueemilian
 */
public interface MesaInterface {
         public List<ModelMesa> listar();
    public List<ModelMesa> list(int id);
    public boolean Register(ModelMesa mesa);
    public boolean Update(ModelMesa mesa);
    public boolean Delete(ModelMesa mesa);
    public List<ModelMesa> Search(String mesa);
    
}
