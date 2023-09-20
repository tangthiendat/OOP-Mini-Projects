package product.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {

    public ArrayList<Product> findAll() {
        final String SQL = "SELECT * FROM product";
        ArrayList<Product> productList = new ArrayList<>();
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productList.add(new Product(result.getString("product_id"),
                        result.getString("product_name"), result.getDouble("unit_price"),
                        result.getInt("quantity"), result.getString("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public void save(ArrayList<Product> productList) {
        final String SQL = "INSERT INTO product(product_id, product_name, unit_price, quantity, status) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            for (Product product : productList) {
                preparedStatement.setString(1, product.getProductID());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3, product.getUnitPrice());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setString(5, product.getStatus());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Product selectedProduct){
        final String SQL = "UPDATE product SET product_name = ?, unit_price = ?, quantity = ?, status = ? WHERE product_id = ?";
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, selectedProduct.getProductName());
            preparedStatement.setDouble(2, selectedProduct.getUnitPrice());
            preparedStatement.setInt(3, selectedProduct.getQuantity());
            preparedStatement.setString(4, selectedProduct.getStatus());
            preparedStatement.setString(5, selectedProduct.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Product selectedProduct){
        final String SQL = "DELETE FROM product WHERE product_id = ?";
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, selectedProduct.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findByName(String productName){
        final String SQL = "SELECT * FROM product WHERE LOWER(product_name) = ?";
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            preparedStatement.setString(1, productName.toLowerCase());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Product(result.getString("product_id"),
                        result.getString("product_name"), result.getDouble("unit_price"),
                        result.getInt("quantity"), result.getString("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Product findByID(String productID){
        final String SQL = "SELECT * FROM product WHERE LOWER(product_id) = ?";
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            preparedStatement.setString(1, productID.toLowerCase());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Product(result.getString("product_id"),
                        result.getString("product_name"), result.getDouble("unit_price"),
                        result.getInt("quantity"), result.getString("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Product> findByNameKeyword(String productName){
        final String SQL = "SELECT * FROM product WHERE LOWER(product_name) LIKE ?";
        ArrayList<Product> productList = new ArrayList<>();
        try (
                Connection connection = DBUtils.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            preparedStatement.setString(1, "%" + productName.toLowerCase() + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productList.add(new Product(result.getString("product_id"),
                        result.getString("product_name"), result.getDouble("unit_price"),
                        result.getInt("quantity"), result.getString("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

}
