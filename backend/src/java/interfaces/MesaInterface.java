package interfaces;

import java.util.List;
import model.ModelMesa;

/**
 *
 * @author josueemilian
 */
public interface MesaInterface {
    public List<ModelMesa> listar();
    public List<ModelMesa> list(int id); //
    public ModelMesa listIndividual(int id);//
    public boolean Register(ModelMesa mesa);
    public boolean Update(ModelMesa mesa); //Update estado de las mesas true or false
    public boolean UpdateIndividual(ModelMesa mesa); // update general de la mesa
    public boolean Delete(ModelMesa mesa);
    public List<ModelMesa> Search(String mesa);
    
}
