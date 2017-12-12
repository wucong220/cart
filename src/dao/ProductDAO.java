package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {
	
	public static void main(String[] args) {
		System.out.println(new ProductDAO().listProduct().size());
	}
	
	public List<Product> listProduct() {
		List<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "select * from product order by id desc";
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8", "root", "admin");
				PreparedStatement pStatement = connection.prepareStatement(sql);) {
			pStatement.execute();
			ResultSet set = pStatement.getResultSet();
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				float price = set.getFloat(3);

				Product product = new Product();
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				products.add(product);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
