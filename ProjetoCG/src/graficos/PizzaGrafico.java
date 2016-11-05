package graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PizzaGrafico {
	
	private ArrayList<Dados> dados;
	private int quantidadeItens;
	
	private double duploPI = 2*Math.PI;
	private Graphics graphics;
	private ArrayList<Cores> cores = new ArrayList<>();
	private Cores corRGB;
	private Painel panelGrafico;
	
	private Color[] padraoCores = new Color[]{ 
			Color.BLUE, 
			Color.RED, 
			Color.GREEN, 
			Color.YELLOW, 
			Color.ORANGE, 
			Color.CYAN, 
			Color.DARK_GRAY, 
			Color.MAGENTA, 
			Color.PINK };
	
	private int contador = 0;

	public PizzaGrafico(ArrayList<Dados> dados, Painel panelGrafico) {
		this.dados = dados;
		this.quantidadeItens = dados.size();
		
		this.panelGrafico = panelGrafico;
	}
	
	public void desenharGrafico(BufferedImage bi) {
		//limpa arraylist de cores rgb
		cores.clear();
		
		//Ponto central da tela
		int origemX = 250;
		int origemY = 200;
		
		graphics = bi.createGraphics();
		
		int raio = 150;
		double anguloFatia = 0.0;
		double anguloFatiaAnterior = 0.0;

		for (int i = 0; i < quantidadeItens; i++) {
			// Calcula o angulo da Fatia
			anguloFatia = duploPI * dados.get(i).getPorcentagem() / 100.0 + anguloFatiaAnterior;
			
			//Passando os angulos de radiano pra grau
			int anguloGrausAtual = (int)Math.toDegrees(anguloFatia);
			int anguloGrausAnterior = (int)Math.toDegrees(anguloFatiaAnterior);
			
			// Salva para a legenda.
			Color cor = padraoCores[contador];
			corRGB = new Cores(cor);
			cores.add(corRGB);
			
			//Corre��o de GAP.
			if (anguloGrausAtual == 359) {
				anguloGrausAtual= 360;
			}
			
			//pintando a pizza
			graphics.setColor(cor);
			graphics.fillArc(100, 50, 2*raio, 2*raio, anguloGrausAnterior, anguloGrausAtual-anguloGrausAnterior);
			//
			
			anguloFatiaAnterior = anguloFatia;
			
			// Contador de cores.
			contador++;
			if (contador == 9){
				contador = 0;
			}
		}
		
		//desenhando borda da pizza
		CircunferenciaPontoMedio circunferencia = new CircunferenciaPontoMedio();
		circunferencia.CircPontoMedio(origemX, origemY, raio, bi);
		
		//passando array de cores para a pintura das legendas
		panelGrafico.gerarLegendas(this.cores);
	}
	
}
