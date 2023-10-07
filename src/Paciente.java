public class Paciente {
    public String id;
    public String nombreCompleto;

    public String getId(){
        return this.id;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public Paciente(String id, String nombre){
        this.id=id;
        this.nombreCompleto=nombre;
    }
}
