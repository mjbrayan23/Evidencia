public class Doctor {
    public String Id;
    public String Nombre;

    public String Especialidad;

    public String getId() {
        return this.Id;
    }

    public String getNombre(){
        return this.Nombre;
    }

    public String getEspecialidad(){
        return this.Especialidad;
    }

    public Doctor(String id, String nombre, String especialidad){
        this.Id=id;
        this.Nombre=nombre;
        this.Especialidad=especialidad;
    }
}
