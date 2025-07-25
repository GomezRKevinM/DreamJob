package com.talento_tech.BolsaEmpleo.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.talento_tech.BolsaEmpleo.Entities.*;
import com.talento_tech.BolsaEmpleo.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

@Service
public class ServiceUsuario {
    private final UsuarioRepository usuarioRepository;
    private final ServiceEmpleado serviceEmpleado;
    private final ServiceEmpresa serviceEmpresa;
    private final ServiceEmpleador serviceEmpleador;
    private final ServiceAplicacion serviceAplicacion;
    private final ServiceOfertaEmpleo serviceOfertaEmpleo;

    private Usuario usuarioEnSesion;
    private UserSesion sesion;

    @Autowired
    public ServiceUsuario(UsuarioRepository usuarioRepository,
                          ServiceEmpleado serviceEmpleado,
                          ServiceEmpresa serviceEmpresa,
                          ServiceEmpleador serviceEmpleador,
                          ServiceAplicacion serviceAplicacion,
                          ServiceOfertaEmpleo serviceOfertaEmpleo) {
        this.usuarioRepository = usuarioRepository;
        this.serviceEmpleado = serviceEmpleado;
        this.serviceEmpresa = serviceEmpresa;
        this.serviceEmpleador = serviceEmpleador;
        this.serviceAplicacion = serviceAplicacion;
        this.serviceOfertaEmpleo = serviceOfertaEmpleo;
    }

    public ResponseDto getAllUsers() {
        try {
            List<Usuario> users = usuarioRepository.findAll();
            return new ResponseDto("Lista de usuarios obtenida exitosamente", users, 200);
        } catch (Exception e) {
            return new ResponseDto("Error al obtener la lista de usuarios", e.getMessage(), 500);
        }
    }

    public ResponseDto getUserByID(Long id) {
        return usuarioRepository.findById(id)
                .map(user -> new ResponseDto("Usuario encontrado", user, 200))
                .orElse(new ResponseDto("Usuario no encontrado", null, 404));
    }

    public ResponseDto agregarUsuario(Usuario user) {
        try {
            int result = usuarioRepository.save(user);
            return result > 0
                    ? new ResponseDto("Usuario agregado exitosamente", user, 201)
                    : new ResponseDto("No se pudo agregar el usuario", null, 400);
        } catch (Exception e) {
            return new ResponseDto("Error al agregar usuario", e.getMessage(), 500);
        }
    }

    public ResponseDto editarUsuario(Usuario usuario) {
        try {
            int result = usuarioRepository.update(usuario);
            return result > 0
                    ? new ResponseDto("Usuario actualizado exitosamente", usuario, 200)
                    : new ResponseDto("No se pudo actualizar el usuario", null, 400);
        } catch (Exception e) {
            return new ResponseDto("Error al actualizar usuario", e.getMessage(), 500);
        }
    }

    public ResponseDto eliminarUsuario(Long id) {
        try {
            int result = usuarioRepository.deleteById(id);
            return result > 0
                    ? new ResponseDto("Usuario eliminado exitosamente", null, 200)
                    : new ResponseDto("No se pudo eliminar el usuario", null, 400);
        } catch (Exception e) {
            return new ResponseDto("Error al eliminar usuario", e.getMessage(), 500);
        }
    }

    public int contarUsuarios() {
        return usuarioRepository.count();
    }

    public ResponseDto login(String input, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsernameOrEmailAndPassword(input, password);

        if (usuario.isPresent()) {
            usuarioEnSesion = usuario.get();
            return new ResponseDto("Usuario autenticado exitosamente", usuarioEnSesion, 200);
        } else {
            return new ResponseDto("Credenciales inválidas", null, 401);
        }
    }

    public ResponseDto logout() {
        if (usuarioEnSesion != null) {
            usuarioEnSesion = null;
            return new ResponseDto("Sesión cerrada exitosamente", null, 200);
        } else {
            return new ResponseDto("El usuario no ha iniciado sesión", null, 401);
        }
    }

    public ResponseDto getUserSession() {
        if (usuarioEnSesion == null) {
            return new ResponseDto("No hay usuario en sesión", null, 404);
        }else{
            sesion = new UserSesion(usuarioEnSesion);
            switch (usuarioEnSesion.getRol()) {
                case "empleado":
                    ResponseDto empleadoResp = serviceEmpleado.getEmpleadoByUser(usuarioEnSesion.getId());
                    sesion.setRolEmpleado((Empleado) empleadoResp.getData());
                    ResponseDto aplicacionesDelEmpleado = serviceAplicacion.getByEmpleado(sesion.getRolEmpleado().getEmpleado_id());
                    @SuppressWarnings("unchecked")
                    List<Aplicacion> aplicacions = (List<Aplicacion>) aplicacionesDelEmpleado.getData();
                    ArrayList<OfertaEmpleo> ofertasAplicadas = new ArrayList<>();
                    aplicacions.forEach( aplicacion -> {
                        OfertaEmpleo oferta = (OfertaEmpleo) serviceOfertaEmpleo.obtenerPorId(aplicacion.getIdOfertaEmpleo()).getData();
                        oferta.setAplicacion_id(aplicacion.getId());
                        ofertasAplicadas.add(oferta);
                    });
                    sesion.getRolEmpleado().setOfertasAplicadas(ofertasAplicadas);
                    break;
                case "empresa":
                    ResponseDto empresaResp = serviceEmpresa.getEmpresaByUser(usuarioEnSesion.getId());
                    sesion.setRolEmpresa((Empresa) empresaResp.getData());
                    break;
                case "empleador":
                    ResponseDto empleadorResp = serviceEmpleador.getEmpleadorByUser(usuarioEnSesion.getId());
                    sesion.setRolEmpleador((Empleador) empleadorResp.getData());
                    break;
                default:
                    return new ResponseDto("Este rol no existe", "este usuario es un admin", 200);
            }

            return new ResponseDto("Usuario en sesión", sesion, 200);
        }
    }

    public ResponseDto actualizarRol(Usuario usuario) {
        try {
            int filasAfectadas = usuarioRepository.updateRol(usuario.getId(), usuario.getRol());
            if (filasAfectadas > 0) {
                return getUserByID(usuario.getId());
            } else {
                return new ResponseDto("Rol no actualizado, no se encontró el usuario", null, 404);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al actualizar rol del usuario", e.getMessage(), 500);
        }
    }

    public ResponseDto editarUsuarioImagen(Usuario usuario) {
        try {
            int result = usuarioRepository.updateImagen(usuario);
            return result > 0
                    ? new ResponseDto("Imagen de usuario actualizada exitosamente", usuario, 200)
                    : new ResponseDto("No se pudo actualizar la imagen del usuario", null, 400);
        } catch (Exception e) {
            return new ResponseDto("Error al actualizar imagen", e.getMessage(), 500);
        }
    }


    public ResponseDto cambiarPassword(Usuario usuario){
        try {
            int filasAfectadas = usuarioRepository.updatePassword(usuario);
            if (filasAfectadas > 0) {
                return new ResponseDto("Contraseña actualizada exitosamente", filasAfectadas, 200);
            } else {
                return new ResponseDto("No se pudo cambiar la contraseña", null, 500);
            }
        }catch (Exception e){
            return new ResponseDto("Error al actualizar cambiar contraseña: "+e.getMessage(), e.getMessage(), 500);
        }
    }

}
