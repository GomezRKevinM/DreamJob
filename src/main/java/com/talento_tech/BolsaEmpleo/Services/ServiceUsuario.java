package com.talento_tech.BolsaEmpleo.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.SystemInfo;
import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Entities.tipoID;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ServiceUsuario {
    private static final Archivos gestor = new Archivos();
    private String ipAddress;
    private Usuario user;

    public ArrayList<Usuario> getAllUsers( HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        SystemInfo infoGeneral = new SystemInfo("DB", "Se ha solicitado la lista de usuarios desde IP: " + ipAddress);
        gestor.escribirArchivo("./src/main/java/com/talento_tech/BolsaEmpleo/logs/log.txt", infoGeneral.toString());
        String sql = "SELECT * FROM usuarios";

        try(Connection connection = DatabaseConexion.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Usuario> users = new ArrayList<Usuario>();
            while (rs.next()) {
                tipoID tipoID = com.talento_tech.BolsaEmpleo.Entities.tipoID.valueOf(rs.getString("tipoid"));
            
                Usuario user = new Usuario();
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIdentificacion(rs.getString("identificacion"));
                user.setEmail(rs.getString("email"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTelefono(rs.getString("telefono"));
                user.setDireccion(rs.getString("direccion"));
                user.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                user.setIdentificacion(rs.getString("identificacion"));
                user.setTipoID(tipoID);
                user.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                user.setUltimaModificacion(rs.getTimestamp("fecha_ultima_modificacion"));
                users.add(user);
                SystemInfo userInfo = new SystemInfo("DB", "Se ha obtenido un usuario desde la base de datos: " + user.getUsername());
                gestor.escribirArchivo("./src/main/java/com/talento_tech/BolsaEmpleo/logs/logDB.txt", userInfo.toString());
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            SystemInfo infoError = new SystemInfo("DB", "Error al obtener la lista de usuarios desde el servicio de usuario: " + e.getMessage());
            gestor.escribirArchivo("./src/main/java/com/talento_tech/BolsaEmpleo/logs/log.txt", infoError.toString());
            return new ArrayList<Usuario>();
        }
    }
    
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password, email, nombre, apellido, telefono, direccion, identificacion, tipoid, fecha_nacimiento) VALUES (?, crypt(?, gen_salt('bf')), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getNombre());
            pstmt.setString(5, usuario.getApellido());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getIdentificacion());
            pstmt.setString(9, usuario.getTipoID().name());
            pstmt.setDate(10, usuario.getFechaNacimiento());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Usuario agregado con éxito: " + usuario.getUsername());
            } else {
                System.out.println("Error al agregar el usuario: " + usuario.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuarios WHERE user_id = ?";
        
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado con éxito: " + id);
            } else {
                System.out.println("No se encontró el usuario con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET username = ?, password = crypt(?, gen_salt('bf')), email = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ?, identificacion = ?, tipoid = ?, fecha_nacimiento = ? WHERE user_id = ?";
        
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getNombre());
            pstmt.setString(5, usuario.getApellido());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getIdentificacion());
            pstmt.setString(9, usuario.getTipoID().name());
            pstmt.setDate(10, usuario.getFechaNacimiento());
            pstmt.setLong(11, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Usuario editado con éxito: " + usuario.getUsername());
            } else {
                System.out.println("Error al editar el usuario: " + usuario.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario login(String username, String password) {
        if(user != null) {
            throw new IllegalStateException("El usuario ya ha iniciado sesión.");
        }
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario y la contrasena deben ser proporcionados.");
        }

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = crypt(?, password)";
        Usuario usuario = null;

        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("user_id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setTipoID(com.talento_tech.BolsaEmpleo.Entities.tipoID.valueOf(rs.getString("tipoid")));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuario.setUltimaModificacion(rs.getTimestamp("fecha_ultima_modificacion"));
                
                this.user = usuario;
                System.out.println("Usuario autenticado: " + username);
            } else {
                System.out.println("Credenciales inválidas para el usuario: " + username);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al autenticar el usuario: " + username, e);
        }
        return usuario;
    }

    public void logout() {
        if (user != null) {
            System.out.println("Usuario " + user.getUsername() + " ha cerrado sesión.");
            user = null;
        } else {
            System.out.println("No hay usuario autenticado para cerrar sesión.");
        }
    }

    public Usuario getUserSession(){
        return user;
    }

    public Usuario validarCredenciales(String email, String rawPassword) {
        String sql = """
            SELECT * 
            FROM usuarios 
            WHERE email = ? 
            AND password = crypt(?, password)
        """;

        try (Connection cn = DatabaseConexion.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, rawPassword);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null; // credenciales inválidas
                }

                // mapear el usuario (idéntico a getAllUsers)
                Usuario u = new Usuario();
                u.setId(rs.getLong("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                // …añade lo que necesites
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
