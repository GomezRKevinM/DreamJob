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
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ServiceUsuario {
    private static final Archivos gestor = new Archivos();
    private String ipAddress;
    private Usuario user;

    public ResponseDto getAllUsers( HttpServletRequest request){
        if(request == null){
            System.out.println("Request is null");
        }else{
            String ipAddress = request.getRemoteAddr();
        }

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
            return new ResponseDto("Lista de usuarios obtenida exitosamente", users, 200);
        } catch (SQLException e) {
            e.printStackTrace();
            SystemInfo infoError = new SystemInfo("DB", "Error al obtener la lista de usuarios desde el servicio de usuario: " + e.getMessage());
            gestor.escribirArchivo("./src/main/java/com/talento_tech/BolsaEmpleo/logs/log.txt", infoError.toString());
            return new ResponseDto("Error al obtener la lista de usuarios", e, 500);
        }
    }
    
    public ResponseDto agregarUsuario(Usuario usuario) {
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
                return new ResponseDto("Usuario agregado exitosamente", usuario, 201);
            } else {
                return new ResponseDto("Error al agregar el usuario", null, 400);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al agregar el usuario", e, 500);
        }
    }

    public ResponseDto eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuarios WHERE user_id = ?";
        
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return new ResponseDto("Usuario eliminado exitosamente", rowsAffected, 200);
            } else {
                return new ResponseDto("Error al eliminar el usuario", rowsAffected, 404);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al eliminar el usuario", e, 500);
        }
    }

    public ResponseDto editarUsuario(Usuario usuario) {
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
                return new ResponseDto("Usuario editado exitosamente", filasAfectadas, 201);
            } else {
                return new ResponseDto("Error al editar el usuario",filasAfectadas, 400);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al editar el usuario", e, 500);
        }
    }

    public ResponseDto login(String username, String password) {
        if(user != null) {
            return new ResponseDto("El usuario ya ha iniciado sesion", null, 401);
        }
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return new ResponseDto("Los campos username y password son obligatorios", null, 400);
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
                return new ResponseDto("Usuario autenticado exitosamente", usuario, 200);
            } else {
                return new ResponseDto("Credenciales inválidas", null, 401);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al autenticar el usuario", e, 500);
        }
    }

    public ResponseDto logout() {
        if (user != null) {
            user = null;
            return new ResponseDto("Sesion cerrada exitosamente", null, 200);
        } else {
            return new ResponseDto("El usuario no ha iniciado sesion", null, 401);
        }
    }

    public Usuario getUserSession(){
        return user;
    }


    public Integer contarUsuarios(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        SystemInfo infoGeneral = new SystemInfo("DB", "Se ha solicitado el conteo de usuarios desde IP: " + ipAddress);
        gestor.escribirArchivo("./src/main/java/com/talento_tech/BolsaEmpleo/logs/log.txt", infoGeneral.toString());
        String sql = "SELECT COUNT(*) AS total FROM usuarios";
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
