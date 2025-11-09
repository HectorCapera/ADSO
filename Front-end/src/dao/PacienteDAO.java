package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;
import conexion.ConexionBD;

public class PacienteDAO {

    // Método para insertar pacientes
    public void insertarPaciente(Paciente paciente) {
        String sql = "INSERT INTO PACIENTES (Nombre, Apellido, Correo, Telefono, Direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setString(3, paciente.getCorreo());
            stmt.setString(4, paciente.getTelefono());
            stmt.setString(5, paciente.getDireccion());
            stmt.executeUpdate();
            System.out.println("✅ Paciente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar paciente: " + e.getMessage());
        }
    }


    // Método para listar pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTES";
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("ID_Paciente"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                p.setCorreo(rs.getString("Correo"));
                p.setTelefono(rs.getString("Telefono"));
                p.setDireccion(rs.getString("Direccion"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pacientes: " + e.getMessage());
        }
        return lista;
    }


    // Método para actualizar pacientes
    public void actualizarPaciente(Paciente paciente) {
        String sql = "UPDATE PACIENTES SET Nombre=?, Apellido=?, Correo=?, Telefono=?, Direccion=? WHERE ID_Paciente=?";
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setString(3, paciente.getCorreo());
            stmt.setString(4, paciente.getTelefono());
            stmt.setString(5, paciente.getDireccion());
            stmt.setInt(6, paciente.getId());
            stmt.executeUpdate();
            System.out.println("🔄 Paciente actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar paciente: " + e.getMessage());
        }
    }

    // Método para eliminar pacientes
    public void eliminarPaciente(int idPaciente) {
        String sql = "DELETE FROM PACIENTES WHERE ID_Paciente=?";
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            stmt.executeUpdate();
            System.out.println("🗑️ Paciente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar paciente: " + e.getMessage());
        }
    }

}

