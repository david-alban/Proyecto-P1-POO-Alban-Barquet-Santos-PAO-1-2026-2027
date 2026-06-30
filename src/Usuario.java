
public abstract class Usuario {
    
    protected String codigoUnico;
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contraseña;
    protected String correo;
    protected Rol rol;

    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, Rol rol) {
        
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombre = nombres;
        this.apellido = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
    }

    public String getCodigoUnico() { 
        return codigoUnico; 
    }
    public void setCodigoUnico(String codigoUnico) { 
        this.codigoUnico = codigoUnico; 
    }

    public String getCedula() { 
        return cedula; 
    }
    public void setCedula(String cedula) { 
        this.cedula = cedula; 
    }

    public String getNombres() { 
        return nombre; 
    }
    public void setNombres(String nombres) { 
        this.nombre = nombres; 
    }

    public String getApellidos() { 
        return apellido; 
    }
    public void setApellidos(String apellidos) { 
        this.apellido = apellidos; 
    }

    public String getUsuario() { 
        return usuario; 
    }
    public void setUsuario(String usuario) { 
        this.usuario = usuario; 
    }

    public String getContraseña() { 
        return contraseña; 
    }
    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña; 
    }

    public String getCorreo() { 
        return correo; 
    }
    public void setCorreo(String correo) { 
        this.correo = correo; 
    }

    public Rol getRol() { 
        return rol; 
    }
    public void setRol(Rol rol) { 
        this.rol = rol; 
    }


    protected abstract void consultarEntradas();
}
