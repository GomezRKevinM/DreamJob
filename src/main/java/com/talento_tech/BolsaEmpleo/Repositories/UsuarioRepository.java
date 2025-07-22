package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapper para convertir ResultSet a Usuario
    private RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) -> {
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
        user.setTipoID(com.talento_tech.BolsaEmpleo.Entities.tipoID.valueOf(rs.getString("tipoid")));
        user.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        user.setUltimaModificacion(rs.getTimestamp("fecha_ultima_modificacion"));
        user.setRol(rs.getString("rol"));
        user.setImagen(rs.getString("imagen"));
        return user;
    };

    // Método para obtener todos los usuarios
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios";
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    // Método para encontrar usuario por ID
    public Optional<Usuario> findById(Long id) {
        String sql = "SELECT * FROM usuarios WHERE user_id = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{id}, usuarioRowMapper);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Método para agregar usuario
    public int save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (" +
                "username, password, identificacion, email, nombre, apellido, " +
                "telefono, direccion, fecha_registro, tipoid, fecha_nacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::tipoid, ?)";

        return jdbcTemplate.update(sql,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getIdentificacion(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getFechaRegistro(),
                usuario.getTipoID().name(),
                usuario.getFechaNacimiento()
        );
    }

    // Método para actualizar usuario
    public int update(Usuario usuario) {
        String sql = "UPDATE usuarios SET " +
                "username = ?, password = ?, identificacion = ?, email = ?, " +
                "nombre = ?, apellido = ?, telefono = ?, direccion = ?, " +
                "tipoid = ?::tipoid, fecha_nacimiento = ? " +
                "WHERE user_id = ?";

        return jdbcTemplate.update(sql,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getIdentificacion(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getTipoID().name(),
                usuario.getFechaNacimiento(),
                usuario.getId()
        );
    }

    // Método para eliminar usuario
    public int deleteById(Long id) {
        String sql = "DELETE FROM usuarios WHERE user_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Método para contar usuarios
    public int count() {
        String sql = "SELECT COUNT(*) FROM usuarios";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Optional<Usuario> findByUsernameOrEmailAndPassword(String input, String password) {
        // Regex pattern for basic email validation
        final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

        String sql;
        Object queryParam;

        if (EMAIL_PATTERN.matcher(input).matches()) {
            // If input matches email pattern, search by email
            sql = "SELECT * FROM usuarios WHERE email = ? AND password = crypt(?, password)";
            queryParam = input;
        } else {
            // Otherwise search by username
            sql = "SELECT * FROM usuarios WHERE username = ? AND password = crypt(?, password)";
            queryParam = input;
        }

        try {
            Usuario user = jdbcTemplate.queryForObject(sql, new Object[]{queryParam, password}, usuarioRowMapper);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int updateRol(Long id, String rol) {
        String sql = "UPDATE usuarios SET rol = ?::user_role WHERE user_id = ?";
        return jdbcTemplate.update(sql, rol, id);
    }

    public int updateImagen(Usuario usuario) {
        String sql = "UPDATE usuarios SET imagen = ?, username = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, usuario.getImagen(),usuario.getUsername(), usuario.getId());
    }

    public int updatePassword(Usuario usuario) {
        String sql = "UPDATE usuarios SET password = crypt(?,gen_salt('md5')) WHERE user_id = ?";
        return jdbcTemplate.update(sql, usuario.getPassword(), usuario.getId());
    }

}