package model;
import java.util.Date;
/**
 *
 * @author josueemilian
 */
public class ModelTransaction {
    //VAR CLIENTE
    private int idCliente;
    private String nombreCliente;
    private String nitCliente;
    private String razonSocialCliente;
    private String nicknameCliente;
    private String direccionCliente;
    private String telefonoCliente;
    
    //VAR PEDIDO
    private int idPedido;
    private int idMesa;
    private int idEmpleado;
    private double totalPedido;
    private String observacionPedido;
    private boolean estadoPedido;
    private Date fechaPedido;
    
    //VAR DETALLE PEDIDO
    private int idDetallePedido;
    private int idCombo;
    private int cantidadDetalle;
    private double precioDetalle;
    private double totalLineaDetalle;
    private String observacionDetalle;
    private boolean estadoDetalle;
    private Date fechaDetalle;

    public ModelTransaction() {
    }

    public ModelTransaction(int idCliente, String nombreCliente, String nitCliente, String razonSocialCliente, String nicknameCliente, String direccionCliente, String telefonoCliente, int idPedido, int idMesa, int idEmpleado, double totalPedido, String observacionPedido, boolean estadoPedido, Date fechaPedido, int idDetallePedido, int idCombo, int cantidadDetalle, double precioDetalle, double totalLineaDetalle, String observacionDetalle, boolean estadoDetalle, Date fechaDetalle) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.nitCliente = nitCliente;
        this.razonSocialCliente = razonSocialCliente;
        this.nicknameCliente = nicknameCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idEmpleado = idEmpleado;
        this.totalPedido = totalPedido;
        this.observacionPedido = observacionPedido;
        this.estadoPedido = estadoPedido;
        this.fechaPedido = fechaPedido;
        this.idDetallePedido = idDetallePedido;
        this.idCombo = idCombo;
        this.cantidadDetalle = cantidadDetalle;
        this.precioDetalle = precioDetalle;
        this.totalLineaDetalle = totalLineaDetalle;
        this.observacionDetalle = observacionDetalle;
        this.estadoDetalle = estadoDetalle;
        this.fechaDetalle = fechaDetalle;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public String getNicknameCliente() {
        return nicknameCliente;
    }

    public void setNicknameCliente(String nicknameCliente) {
        this.nicknameCliente = nicknameCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getObservacionPedido() {
        return observacionPedido;
    }

    public void setObservacionPedido(String observacionPedido) {
        this.observacionPedido = observacionPedido;
    }

    public boolean isEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(boolean estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public int getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(int cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public double getPrecioDetalle() {
        return precioDetalle;
    }

    public void setPrecioDetalle(double precioDetalle) {
        this.precioDetalle = precioDetalle;
    }

    public double getTotalLineaDetalle() {
        return totalLineaDetalle;
    }

    public void setTotalLineaDetalle(double totalLineaDetalle) {
        this.totalLineaDetalle = totalLineaDetalle;
    }

    public String getObservacionDetalle() {
        return observacionDetalle;
    }

    public void setObservacionDetalle(String observacionDetalle) {
        this.observacionDetalle = observacionDetalle;
    }

    public boolean isEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(boolean estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public Date getFechaDetalle() {
        return fechaDetalle;
    }

    public void setFechaDetalle(Date fechaDetalle) {
        this.fechaDetalle = fechaDetalle;
    }
    
    
}
