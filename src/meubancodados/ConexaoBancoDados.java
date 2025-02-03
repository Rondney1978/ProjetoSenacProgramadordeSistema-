package meubancodados;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class ConexaoBancoDados {
	static String URL = "jdbc:mysql://localhost:3306/planta_eva";
	static String USER = "root";
	static String PASSWORD = "123456";

	public static Connection conexao_com_banco_dados() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Erro,Quebrou Tudo");
			return null;	}
	}
	
        public static void inserirDados() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do produto:");
		String  nome = scanner.nextLine();
		
		System.out.println("Digite o preço do produto:");
		BigDecimal preco = new BigDecimal(scanner.nextLine());
		
		System.out.println("Digite quantidade do produto:");
		int quantidade = Integer.parseInt(scanner.nextLine()); 
		
		String sql = "INSERT INTO PRODUTOS(NOME, PRECO, QUANTIDADE) values (\"solo arenoso\")";
		
		try(Connection realiza_conexao = conexao_com_banco_dados();
		PreparedStatement cursor = realiza_conexao.prepareStatement(sql)) {
			
		cursor.setString(1,nome);
		cursor.setBigDecimal(2,preco);
		cursor.setInt(3,quantidade);
		
	    cursor.executeUpdate();
	    System.out.println("Iserido com sucesso");}}
	
		public static void consultarDados() throws SQLException {
			String sql = "select * from produtoS";
			try ( Connection realiza_conexao = conexao_com_banco_dados();
					Statement cursor = realiza_conexao.createStatement();
					ResultSet resultado_consulta = cursor.executeQuery(sql)
							){
				
				while (resultado_consulta.next())
					{
					int id = resultado_consulta.getInt("id");
					String nome = resultado_consulta.getString("nome");
					double preco = resultado_consulta.getDouble("preco");
					int quantidade = resultado_consulta.getInt("quantidade");
					
					System.out.printf("ID, %d, Nome: %s, Preço, %.2f, Quantidade: %d%n", id, nome, preco, quantidade);
				}
//				System.out.println("resultado_consulta");
			} catch (Exception e) {
				e.printStackTrace();
		}
}
		public static void AtualizarDados() throws SQLException {
			return;
		}
		
		public static void DeletaDados() throws SQLException {
			
			//System.out.println("Dados Cadastrados");
			consultarDados();
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Digite o nome do produto que deseja execluir");
			String nome = scanner.nextLine();
			
			String sql = "DELETE FROM produtos WHERE nome ?";
			
			try (Connection conexao = conexao_com_banco_dados();
					PreparedStatement cursor = conexao.prepareStatement(sql)){
				cursor.setString(1, nome);
				cursor.executeUpdate();
			}
		}
		
	    public static void main(String[] args) throws SQLException {
		
		while(true) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nEscolha uma opção:");
		System.out.println("1.Inseri Dados");
		System.out.println("2.Consulta Dados");
		System.out.println("3.Atualiza Dados");
		System.out.println("4.Deleta Dados");
		System.out.println("5.Sair \n ");
		
		int opcao = Integer.parseInt(scanner.nextLine());
		
		switch(opcao) {
		case 1:
			inserirDados();
			break;
		case 2:
			consultarDados();
			break;
		case 4:
			DeletaDados();
		}
		//Connection conexao = conexao_com_banco_dados();
		//if (conexao != null) {
		//	System.out.println("Conexão bem sucedida!");
		//} else {
			//System.out.println("Falha na Conexão");
		inserirDados();
		}
}}

