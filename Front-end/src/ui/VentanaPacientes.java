package ui;

import dao.PacienteDAO;
import model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPacientes extends JFrame {

    private JTextField txtId, txtNombre, txtApellido, txtCorreo, txtTelefono, txtDireccion;
    private JTextArea txtAreaPacientes;
    private PacienteDAO dao;

    public VentanaPacientes() {
        dao = new PacienteDAO();

        setTitle("Gestión de Pacientes");
        setSize(550, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior - Campos de texto
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));

        panelCampos.add(new JLabel("ID Paciente (para actualizar/eliminar):"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelCampos.add(txtApellido);

        panelCampos.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        panelCampos.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

        panelCampos.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion);

        add(panelCampos, BorderLayout.NORTH);

        // Panel central - área de texto
        txtAreaPacientes = new JTextArea();
        txtAreaPacientes.setEditable(false);
        add(new JScrollPane(txtAreaPacientes), BorderLayout.CENTER);

        // Panel inferior - botones
        JPanel panelBotones = new JPanel();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnListar = new JButton("Listar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnListar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción: Agregar
        btnAgregar.addActionListener(e -> {
            Paciente p = new Paciente();
            p.setNombre(txtNombre.getText());
            p.setApellido(txtApellido.getText());
            p.setCorreo(txtCorreo.getText());
            p.setTelefono(txtTelefono.getText());
            p.setDireccion(txtDireccion.getText());

            dao.insertarPaciente(p);
            JOptionPane.showMessageDialog(this, "✅ Paciente agregado correctamente");
            limpiarCampos();
        });

        // Acción: Listar
        btnListar.addActionListener(e -> {
            List<Paciente> lista = dao.listarPacientes();
            txtAreaPacientes.setText("");
            for (Paciente p : lista) {
                txtAreaPacientes.append(
                        p.getId() + " - " + p.getNombre() + " " + p.getApellido() + " - " +
                                p.getCorreo() + " - " + p.getTelefono() + " - " + p.getDireccion() + "\n"
                );
            }
        });

        // Acción: Actualizar
        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Paciente p = new Paciente();
                p.setId(id);
                p.setNombre(txtNombre.getText());
                p.setApellido(txtApellido.getText());
                p.setCorreo(txtCorreo.getText());
                p.setTelefono(txtTelefono.getText());
                p.setDireccion(txtDireccion.getText());

                dao.actualizarPaciente(p);
                JOptionPane.showMessageDialog(this, "🔄 Paciente actualizado correctamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Debes ingresar un ID válido para actualizar");
            }
        });

        // Acción: Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                dao.eliminarPaciente(id);
                JOptionPane.showMessageDialog(this, "🗑️ Paciente eliminado correctamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Debes ingresar un ID válido para eliminar");
            }
        });
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPacientes().setVisible(true));
    }
}
