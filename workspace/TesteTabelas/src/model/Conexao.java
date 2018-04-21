package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;
	
	public Conexao(){
		url = "jdbc:postgresql://localhost:5432/postgres";
		usuario = "postgres";
		senha = "123456";
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conexão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int executaSQL(String sql ){
		try {
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			con.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	} 
	
	public ResultSet executaBusca(String sql){
		try {
			Statement stm = con.createStatement(1, 0);
			ResultSet rs = stm.executeQuery(sql);
			con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
