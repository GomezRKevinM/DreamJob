package com.talento_tech.BolsaEmpleo.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConexion {
    private static final String URL = "jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.tfgnncxxcoycjuwiceby";
    private static final String PASSWORD = "Talento@Tech#2025"; // Reemplaza con tu contraseña real

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; // Re-lanzar la excepción para manejarla más arriba en la pila si es necesario
        }
        return connection; // Devuelve la conexión sin cerrarla
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            // Aquí puedes realizar operaciones con la conexión
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}