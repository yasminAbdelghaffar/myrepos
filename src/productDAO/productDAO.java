package productDAO;

import java.sql.Connection;
import DBConnection.DBconnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import product.Product;

public class productDAO {

    public ArrayList<Product> GetAllproducts(Product D) {
        String query = "SELECT * FROM products WHERE manufacturer = '" + D.getManufacturer()+ "'";
        DBconnection con = DBconnection.getInstance();
        Connection connect = con.getConnection();
        Statement statement = null;
        ArrayList<Product> products = new ArrayList<>();
        try {

            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductid(rs.getString("productid"));
                product.setType(rs.getString("type"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setProductiondate(rs.getString("productiondate"));
                product.setExpirydate(rs.getString("expirydate"));

                products.add(product);

            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        } finally {
            try {
                connect.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return products;

    }

    public ArrayList<Product> GetAll() {
        String query = "select * from products ";
        DBconnection con = DBconnection.getInstance();
        Connection connect = con.getConnection();
        PreparedStatement statement = null;
        ArrayList<Product> p = new ArrayList<Product>();
        try {
            statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product pr = new Product();
                pr.setProductid(rs.getString("productid"));
                pr.setType(rs.getString("type"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setProductiondate(rs.getString("productiondate"));
                pr.setExpirydate(rs.getString("expirydate"));

                p.add(pr);
            }

        } catch (Exception Ex) {
            System.out.print(Ex.getMessage());
            JOptionPane.showMessageDialog(null, "There are no products found!");
        } finally {
            try {
                connect.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return p;

    }
    
     public int DeleteProduct(Product product) {
        int affected = 0;
        String query = "Delete FROM products WHERE  productiD = '" + product.getProductid() + "'";
        Statement statement = null;
       DBconnection con = DBconnection.getInstance();
        Connection connect = con.getConnection();

        try {
            statement = connect.createStatement();
            affected = statement.executeUpdate(query);
        } catch (SQLException Ex) {
            System.out.print(Ex.getMessage());
            Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, Ex);
        }

        return affected;

    }

    public void update(Product newone, Product oldone) {
        String sql = "UPDATE products SET type= ? ,manufacturer= ? ,productiondate= ?,expirydate= ? WHERE productid= ? ";
        DBconnection con = DBconnection.getInstance();
        Connection connect = con.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, newone.getType());
            pstmt.setString(2, newone.getManufacturer());
            pstmt.setString(3, newone.getProductiondate());
            pstmt.setString(4, newone.getExpirydate());
            pstmt.setString(5, oldone.getProductid());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Updating!");
        }
    }

    public void insert(String id, String type, String manuf, String production, String expiry) {
        Product p = new Product();
        p.setProductid(id);
        p.setType(type);
        p.setManufacturer(manuf);
        p.setProductiondate(production);
        p.setExpirydate(expiry);
        DBconnection con = DBconnection.getInstance();
        Connection connect = con.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connect.prepareStatement("insert into products values (?,?,?,?,?)");
            pstmt.setString(1, id);
            pstmt.setString(2, type);
            pstmt.setString(3, manuf);
            pstmt.setString(4, production);
            pstmt.setString(5, expiry);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data is saved");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, Data wasn't saved!");
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Wrong Input. Please try again!");
        }
    }


}
