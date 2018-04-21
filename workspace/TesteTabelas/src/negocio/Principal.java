package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexao;
import model.Produto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Principal extends JFrame{
	
	public Principal(JScrollPane scrollPane){
		setLayout(new FlowLayout());//tipo de layout
        setSize(new Dimension(600, 200));//tamanho do Formulario
        setLocationRelativeTo(null);//centralizado
        setTitle("Exemplo JTable");//titulo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setando a ação padrão de fechamento do Formulário,
                                                               // neste caso  irá fechar o programa
        add(scrollPane);
	}

	public static void main(String[] args) {
		JTable tabela;
		String colunas[]={"Nome","Descrição","Quantidade","Valor"};
		String dados[][] = null;
		Conexao con = new Conexao();
		String sql;
		int tam = 0;
		/*sql = "INSERT INTO produto (id,nome,descrição,quantidade,valor)"
				+ " values (default,'HUGO BOSS','Perfume',10,120.00)";
		int res = con.executaSQL(sql);
		if(res > 0){
			System.out.println("Cadastrado com sucesso!");
		}
		else{
				System.out.println("Erro durante cadastro!");
		}*/
		/*sql = "INSERT INTO produto (id,nome,descrição,quantidade,valor)"
				+ " values (default,'212 Sexy','Perfume',10,120.00)";
		int res = con.executaSQL(sql);
		if(res > 0){
			System.out.println("Cadastrado com sucesso!");
		}
		else{
				System.out.println("Erro durante cadastro!");
		}
		*/
		sql = "Select * from produto";
		ResultSet rs = con.executaBusca(sql);
		try {
			int i = 0;
			tam = lenght(rs);
			rs.first();
			rs.previous();
			dados = new String[tam][4];
			while(rs.next()){
				dados[i][0] = rs.getString("nome");
				dados[i][1] = rs.getString("descrição");
				dados[i][2] = Integer.toString(rs.getInt("quantidade"));
				dados[i][3] = String.valueOf(rs.getDouble("valor"));
				i++;
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		//instanciando a JTable
        tabela=new JTable(dados,colunas);
        tabela.setPreferredScrollableViewportSize(new Dimension(500,100));//barra de rolagem
        tabela.setFillsViewportHeight(true);
         
                //adicionando a tabela em uma barra de rolagem, ficará envolta , pela mesma 
        JScrollPane scrollPane=new JScrollPane(tabela);
                 
                //adicionando ao JFrame "Formulário" a JTable "Tabela" 
        
        new Principal(scrollPane).setVisible(true);
        
        try{
			List<Produto> produtos = new ArrayList<Produto>();
			int i;
			for(i=0;i<tam;i++){
					Produto produto = new Produto();
					produto.setNome(dados[i][0]);
					produto.setDescricao(dados[i][1]);
					produto.setQuantidade(Integer.parseInt(dados[i][2]));
					produto.setValor(Double.parseDouble(dados[i][3]));
					System.out.println(produto.toString());
					produtos.add(produto);
			}		
			
			ProdutoRelatorio relatorio = new ProdutoRelatorio();
			relatorio.imprimir(produtos);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	private static int lenght(ResultSet rs) throws SQLException {
		int i = 0;
		while(rs.next()){
			i++;
		}
		return i;
	}
}
