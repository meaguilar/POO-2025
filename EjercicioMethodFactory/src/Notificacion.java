import java.sql.Time;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

abstract class Notificacion implements INotificacion {
    protected String remitente;
    protected String destinatario;
    protected String mensaje;
    protected LocalDate fecha;
    protected boolean estado;

    public Notificacion(){}

    public Notificacion(String remitente, String destinatario, String mensaje) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.mensaje = mensaje;
        this.fecha = LocalDate.now();
        this.estado = false;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * <p>Cambiara el estado de false a true despues de 5 segundos, indicando el  estado del mensaje</p>
     */
    protected void marcarEntregadaConRetraso(){


    }

    @Override
    public abstract void enviarMensaje();

}
