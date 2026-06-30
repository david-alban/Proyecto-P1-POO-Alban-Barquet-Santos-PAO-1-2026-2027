import java.util.Date;

public class Compra {
    private int codigoCompra;
    private String tipo;
    private String codigoReferencial;
    private Date fechaCompra;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;

    public static int nextCodigo = 1;

    public Compra(String tipo, String codigoReferencial, int cantidad, double valorPagado, String codigoAficionado) {
        
        this.tipo = tipo;
        this.codigoReferencial = codigoReferencial;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;

        this.codigoCompra = nextCodigo;
        nextCodigo++; 
        
        this.fechaCompra = new Date();
    }

    public int getCodigoCompra() { 
        return codigoCompra; 
    }
    public void setCodigoCompra(int codigoCompra) { 
        this.codigoCompra = codigoCompra; 
    }

    public String getTipo() { 
        return tipo; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public String getCodigoReferencial() { 
        return codigoReferencial; 
    }
    public void setCodigoReferencial(String codigoReferencial) {
         this.codigoReferencial = codigoReferencial; 
    }

    public Date getFechaCompra(){
         return fechaCompra; 
    }
    public void setFechaCompra(Date fechaCompra) { 
        this.fechaCompra = fechaCompra; 
    }

    public int getCantidad() { 
        return cantidad; 
    }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public double getValorPagado() { 
        return valorPagado; 
    }
    public void setValorPagado(double valorPagado) { 
        this.valorPagado = valorPagado; 
    }

    public String getCodigoAficionado() { 
        return codigoAficionado; 
    }
    public void setCodigoAficionado(String codigoAficionado) { 
        this.codigoAficionado = codigoAficionado; 
    }
}

