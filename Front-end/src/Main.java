import dao.PacienteDAO;
import model.Paciente;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PacienteDAO dao = new PacienteDAO();

        // agregar un nuevo paciente

        Paciente nuevo = new Paciente();
        nuevo.setNombre("Laura");
        nuevo.setApellido("Gómez");
        nuevo.setCorreo("laura@example.com");
        nuevo.setTelefono("3001234567");
        nuevo.setDireccion("Calle 45 #10-20");
        dao.insertarPaciente(nuevo);

        nuevo.setNombre("José");
        nuevo.setApellido("Vergara");
        nuevo.setCorreo("jose@example.com");
        nuevo.setTelefono("3026234567");
        nuevo.setDireccion("Calle 12 #1-85");
        dao.insertarPaciente(nuevo);


        // Listar todos los pacientes
        List<Paciente> pacientes = dao.listarPacientes();
        System.out.println("\n📋 Lista de pacientes:");
        for (Paciente p : pacientes) {
            System.out.println(p.getId() + " - " + p.getNombre() + " " + p.getApellido());
        }
    }
}
