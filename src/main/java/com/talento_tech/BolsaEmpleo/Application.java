package com.talento_tech.BolsaEmpleo;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;

public class Application extends JFrame {
    BolsaDeEmpleoApplication bolsaDeEmpleoApplication;

    public Application() {
        bolsaDeEmpleoApplication = new BolsaDeEmpleoApplication();
        setTitle("Bolsa de Empleo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = cargarUsuarios();
        add(panel);
        setVisible(true);

    }

    public JPanel cargarUsuarios(){
        // Aquí se cargarían los usuarios desde la base de datos
        // Por ahora, solo se imprime un mensaje de ejemplo
        System.out.println("Cargando usuarios desde la base de datos...");
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Lista de Usuarios");
        panel.add(title);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        try {
            ServiceUsuario serviceUsuario = new ServiceUsuario();
            ArrayList<Usuario> usuarios = serviceUsuario.getAllUsers();

            for(Usuario usuario : usuarios) {
                JPanel userPanel = new JPanel();
                userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
                userPanel.add(new JLabel("Nombre: " + usuario.getNombre()));
                userPanel.add(new JLabel("Apellido: " + usuario.getApellido()));
                panel.add(userPanel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return panel;
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.setVisible(true);
        System.out.println("Aplicación de Bolsa de Empleo iniciada correctamente.");
    }
    
}