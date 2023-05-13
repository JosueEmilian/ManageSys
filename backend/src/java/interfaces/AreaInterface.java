package interfaces;

import java.util.List;
import model.ModelArea;

/**
 *
 * @author josueemilian
 */
public interface AreaInterface {
     public List<ModelArea> listar();
    public ModelArea list(int id);
    public boolean Register(ModelArea area);
    public boolean Update(ModelArea area);
    public boolean Delete(ModelArea area);
    public List<ModelArea> Search(String area);
}
