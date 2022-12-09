package mx.uv;

public class Evento {
    String id;
    String titulo;
    String fecha;
    String horaInicio;
    String horaFin;
    String lugar;
    String descripcion;
    String usuario;

    public Evento(String id, String titulo, String fecha, String horaInicio, String horaFin, String lugar,
            String descripcion, String usuario) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public Evento() {}
    
    public Evento(String titulo) {
        this.titulo = titulo;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", horaInicio=" + horaInicio
                + ", horaFin=" + horaFin + ", lugar=" + lugar + ", descripcion=" + descripcion + ", usuario=" + usuario
                + "]";
    }
    
}
