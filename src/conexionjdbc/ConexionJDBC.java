package conexionjdbc;

import java.sql.*;

public class ConexionJDBC {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/tienda";
        String usuario = "root";
        String contrasena = "";
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    public static void insertarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.executeUpdate();
            System.out.println("Producto insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarProductos() {
        String query = "SELECT * FROM productos";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                System.out.println(id + " | " + nombre + " | " + descripcion + " | " + precio + " | " + cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Producto actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarProducto(int id) {
        String query = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Producto eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertarProducto("Camiseta", "Camiseta de algodón", 19.99, 100);
        //consultarProductos();
        //actualizarProducto(1, "Camiseta de Manga Larga", "Camiseta de algodón con manga larga", 22.99, 80);
        //eliminarProducto(1);
    }
}
