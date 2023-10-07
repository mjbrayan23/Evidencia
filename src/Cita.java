import java.time.LocalDateTime;
import java.util.Date;

public class Cita {
    public String id;

    public Date fechaHora;

    public String motivo;

    public String idDoctor;

    public String idPaciente;

    public String getId(){
        return this.id;
    }

    public Date getFechaHora() {
        return this.fechaHora;
    }

    public String getMotivo(){
        return this.motivo;
    }

    public String getDoctor() {
        return this.idDoctor;
    }

    public String getPaciente(){
        return  this.idPaciente;
    }

    public Cita(String id, Date fecha, String motivo, String iddoc, String idpac){
        this.id=id;
        this.fechaHora=fecha;
        this.motivo=motivo;
        this.idDoctor=iddoc;
        this.idPaciente=idpac;
    }
}
