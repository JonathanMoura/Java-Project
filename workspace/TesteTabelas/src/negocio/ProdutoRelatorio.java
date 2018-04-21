package negocio;

import java.util.List;

import model.Produto;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ProdutoRelatorio {
	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public ProdutoRelatorio() {
		this.path = "/C:/Users/Jonathan Moura/workspace/TesteTabelas/bin/";// this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}

	// Imprime/gera uma lista de Produtoss
	public void imprimir(List<Produto> produtos) throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Blank_A4.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(produtos));

		JasperExportManager.exportReportToPdfFile(print, "/C:/Users/Jonathan Moura/Downloads/Relatorio_de_Produtos.pdf");
		System.out.println("Download completo.");
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
