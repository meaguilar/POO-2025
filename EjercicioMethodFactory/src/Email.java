
public class Email extends Notificacion{
    private String proveedor;
    private String asunto;

    public Email(){}
    public Email(String remitente, String destinatario, String mensaje, String proveedor, String asunto) {
        super(remitente, destinatario, mensaje);
        this.proveedor = proveedor;
        this.asunto = asunto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando email a " + getDestinatario());
        System.out.println("De: " + getRemitente());
        System.out.println("Fecha: " + getFecha());
        System.out.println("Asunto: " + getAsunto());
        System.out.println("Mensaje: " + getMensaje());
        System.out.println("Proveedor: " + getProveedor());
    }
}
