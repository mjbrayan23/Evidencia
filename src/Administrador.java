import java.util.Objects;

public class Administrador {
    public String id;

    public String password;

    public String getId(){
        return this.id;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean verificarContrasena(String valor) {
        boolean resultado;

        if (this.password == valor) {
            resultado=true;
        }
        else {
            resultado=false;
        }

        return resultado;
    }

    public Administrador(String id, String pass) {
        this.id=id;
        this.password=pass;
    }
}
