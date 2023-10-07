import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Main {
    public static List<Doctor> doctores;
    public static List<Paciente> pacientes;
    public static List<Cita> citas;
    public static List<Administrador> admins;

    public static void agregarDoctor(Doctor doc){
        doctores.add(doc);
    }

    public static void agregarPaciente(Paciente paci){
        pacientes.add(paci);
    }

    public static void crearCita(Cita nueva) {
        citas.add(nueva);
    }

    public static void agregarAdmin(Administrador admin) {
        admins.add(admin);
    }

    public static boolean verificaAcceso(String pass, Administrador admin) {
        boolean resultado;
        resultado=admin.verificarContrasena(pass);
        return resultado;
    }

    public static void cargarDatos() {
        File archivo = null;
        FileReader fr = null;
        String ruta= System.getProperty("user.dir") + "\\db\\";
        String linea;
        String[] array;
        SimpleDateFormat conver = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fecha;
        String nuevoFormato;
        BufferedReader br = null;

        try{
            if(doctores==null) {
                doctores= new ArrayList<Doctor>();
            }
            else {
                doctores.clear();
            }

            if(pacientes==null) {
                pacientes= new ArrayList<Paciente>();
            }
            else {
                pacientes.clear();
            }

            if(citas==null) {
                citas = new ArrayList<Cita>();
            }
            else {
                citas.clear();
            }

            if(admins==null) {
                admins= new ArrayList<Administrador>();
            }
            else {
                admins.clear();
            }

            //doctores
            archivo = new File (ruta+"doctores.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            while((linea=br.readLine())!=null){
                array=null;
                array=linea.split(",");

                if(array.length==3) {
                    Doctor oDoc=new Doctor(array[0],array[1],array[2]);
                    doctores.add(oDoc);
                }
            }

            if( fr != null){
                fr.close();
            }

            //paciente
            archivo = new File (ruta+"pacientes.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            while((linea=br.readLine())!=null){
                array=null;
                array=linea.split(",");

                if(array.length==2) {
                    Paciente oPac=new Paciente(array[0],array[1]);
                    pacientes.add(oPac);
                }
            }

            if( fr != null){
                fr.close();
            }

            //citas
            archivo = new File (ruta+"citas.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            while((linea=br.readLine())!=null){
                array=null;
                array=linea.split(",");

                if(array.length==5) {
                    fecha = conver.parse(array[1]);
                    Cita oCita=new Cita(array[0], fecha, array[2],array[3],array[4]);
                    citas.add(oCita);
                }
            }

            if( fr != null){
                fr.close();
            }

            //administrador
            archivo = new File (ruta+"admin.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            while((linea=br.readLine())!=null){
                array=null;
                array=linea.split(",");

                if(array.length==2) {
                    Administrador oAdmin=new Administrador(array[0], array[1]);
                    admins.add(oAdmin);
                }
            }

            if( fr != null){
                fr.close();
            }

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void guardarDatos(){
        try{
            String ruta;
            FileWriter fw = null;
            PrintWriter pw =null;
            ruta= System.getProperty("user.dir") + "\\db\\";

            fw = new FileWriter(ruta+"doctores.csv");
            pw = new PrintWriter(fw);

            for (Doctor doc: doctores) {
                pw.println(doc.getId() + ","+doc.getNombre()+","+doc.getEspecialidad());
            }

            if(fw!=null){
                fw.close();
            }

            fw = new FileWriter(ruta+"pacientes.csv");
            pw = new PrintWriter(fw);

            for (Paciente pac:pacientes) {
                pw.println(pac.getId()+","+pac.getNombreCompleto());
            }

            if(fw!=null){
                fw.close();
            }

            fw = new FileWriter(ruta+"citas.csv");
            pw = new PrintWriter(fw);

            for (Cita ct:citas) {
                pw.println(ct.getId()+","+(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ct.getFechaHora()))+
                        ","+ct.getMotivo()+","+ct.getDoctor()+","+ct.getPaciente());
            }

            if(fw!=null){
                fw.close();
            }

            fw = new FileWriter(ruta+"admin.csv");
            pw = new PrintWriter(fw);

            for (Administrador ad:admins) {
                pw.println(ad.getId()+","+ad.getPassword());
            }

            if(fw!=null){
                fw.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static boolean VerificaAdmin(String nombre, String pass){
        boolean resultado;
        String usr,ps;
        resultado=false;

        for (Administrador ad:admins) {
            usr=ad.getId();
            ps=ad.getPassword();

            if(Objects.equals(usr, nombre)){
                if(Objects.equals(ps, pass)) {
                    resultado=true;
                    break;
                }
                else {
                    resultado=false;
                    break;
                }
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        String usuario,id,id2,id3,nombre,especialidad, fecha,motivo;
        String contrasena;
        Doctor odoc;
        Paciente opac;
        Cita ocita;
        Administrador oadmin;
        SimpleDateFormat conver = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaaux;

        odoc=null;
        opac=null;
        ocita=null;
        oadmin=null;

        id="";
        id2="";
        id3="";
        nombre="";
        especialidad="";
        fecha="";
        motivo="";
        contrasena="";

        try{
            System.out.println("Cargando datos... un momento por favor");
            cargarDatos();
            System.out.println("Datos cargados exitosamente");
            opcion=0;

            System.out.println("Escriba su nombre de usuario");
            usuario = entrada.nextLine();
            System.out.println("Escriba su contraseña");
            contrasena = entrada.nextLine();
            opcion=0;

            if(VerificaAdmin(usuario,contrasena)){
                while(opcion!=4) {
                    System.out.println("Bienvenido al sistema de citas médicas");
                    System.out.println("Por favor elija una opcion:");
                    System.out.println("1. Alta de médicos");
                    System.out.println("2. Alta de pacientes");
                    System.out.println("3. Crear una cita");
                    System.out.println("4. Salir");
                    opcion=entrada.nextInt();
                    entrada.nextLine();

                    switch (opcion){
                        case 1:
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Ingrese un id para el nuevo médico");
                            id= entrada.nextLine();

                            System.out.println("Ingrese el nombre del médico");
                            nombre= entrada.nextLine();

                            System.out.println("Ingrese la especialidad del médico");
                            especialidad= entrada.nextLine();

                            odoc=new Doctor(id,nombre,especialidad);
                            doctores.add(odoc);
                            System.out.println("Doctor ingresado correctamente");

                            break;
                        case 2:
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Ingrese un id para el nuevo paciente");
                            id= entrada.nextLine();

                            System.out.println("Ingrese el nombre del paciente");
                            nombre= entrada.nextLine();

                            opac=new Paciente(id,nombre);
                            pacientes.add(opac);
                            System.out.println("Paciente ingresado correctamente");
                            break;
                        case 3:
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Ingrese un id para la cita");
                            id= entrada.nextLine();

                            System.out.println("Ingrese el motivo de la cita");
                            motivo= entrada.nextLine();

                            System.out.println("Ingrese la fecha y hora de la cita");
                            System.out.println("En el siguiente formato dd/MM/aaaa HH:mm");
                            fecha= entrada.nextLine();

                            while(fecha.length()!=16) {
                                System.out.println("Formato de fecha inválido");
                                System.out.println("Favor de verificar");
                                System.out.println("Recuerde el formato válido: dd/MM/aaaa HH:mm");
                                fecha= entrada.nextLine();
                            }

                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Lista actual de Doctores:");

                            for (Doctor dc:doctores) {
                                System.out.println("ID | Nombre | Especialidad");
                                System.out.println(dc.Id+" | "+dc.Nombre+" | "+dc.Especialidad);
                            }

                            System.out.println("Escriba un id del doctor que deseé asignar");
                            id2= entrada.nextLine();

                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Lista actual de Pacientes:");

                            for (Paciente pc:pacientes) {
                                System.out.println("ID | Nombre");
                                System.out.println(pc.id+" | "+pc.nombreCompleto);
                            }

                            System.out.println("Escriba el id del paciente que deseé asignar");
                            id3= entrada.nextLine();

                            fechaaux = conver.parse(fecha);

                            ocita=new Cita(id,fechaaux,motivo,id2,id3);
                            citas.add(ocita);
                            System.out.println("Cita ingresada correctamente");
                            break;
                        case 4:
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opción no válida");
                            System.out.println("favor de verificar");
                    }
                }
            }
            else {
                System.out.println("Usuario y/o contraseña incorrecto(s)");
                System.out.println("Favor de verificar");
            }

            entrada.close();
            guardarDatos();
        }
        catch(Exception ex) {
            System.out.println("Ocurrió un error");
            System.out.println(ex.toString());
        }
    }
}
