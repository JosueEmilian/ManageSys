package interfaces;

import java.util.List;
import model.ModelMonitorCocina;

/**
 *
 * @author josueemilian
 */
public interface MonitorCocinaInterface {

    List<ModelMonitorCocina> listar();
    public boolean Update(ModelMonitorCocina monitorCocina);

}
