package sistemasolar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SistemaSolar {
	
	Orbita orb;
	Planetas terra;
	Planetas  sol;
	
	public List<Ponto> sistema = new ArrayList<Ponto>();
	
	public SistemaSolar() {
		
		orb = new Orbita();
		terra = new Planetas();
		sol = new Planetas();
		
		Janela frame = new Janela();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JLabel titulo = new JLabel("ANIMA��O DO SISTEMA SOLAR");
		frame.add(titulo);
		
		limparSistemaSolar();
		orb.midpointElipse(200, 150);
		
		//Configurando a terra
		terra.setX(200);
		terra.setY(0);
		terra.setRaio(30);
		terra.circunferenciaPontoMedio();
		
		Janela.contentPane.desenharPontos(terra.getListaPontos(), Color.BLUE.getRGB());
		
		Janela.contentPane.repaint();
		
		desenharOrbitaESol();
		
		// Parte de cima.
		for (int i = 200; i >= -200; i--) {
			for (int j = 0; j <= 150; j++) {
				if (orb.contem(new Ponto(i, j, 0), orb.getListaPontos())) {
					sistema.add(new Ponto(i, j, 0));	
				}				
			}
		}
				
		// Parte de baixo.
		for (int i = -200; i <= 200; i++) {
			for (int j = 0; j >= -150; j--) {
				if (orb.contem(new Ponto(i, j, 0), orb.getListaPontos())) {
					sistema.add(new Ponto(i, j, 0));	
				}		
			}
		}
				
		Janela.contentPane.repaint();
	}
	
	private void limparSistemaSolar() {
		
		Janela.contentPane.limpaPainel();
	}
	
	private void desenharOrbitaESol() {
		
		// Desenha a elipse.
		Janela.contentPane.desenharPontos(orb.getListaPontos(), Color.ORANGE.getRGB());
		
		sol = new Planetas();
		sol.setX(0);
		sol.setY(0);
		sol.setRaio(50);
		
		// Cria o sol.
		sol.circunferenciaPontoMedio();
		
		// Desenha o sol.
		Janela.contentPane.desenharPontos(sol.getListaPontos(), Color.RED.getRGB());
	}
	
	public boolean animacaoStatus = true;
	
	public void animacao() {
		Thread minhaThread = new Thread() {
			public void run() {
				while (animacaoStatus){
					// codigo para executar
					for (Ponto ponto : sistema) {
						limparSistemaSolar();
						desenharOrbitaESol();
						
						terra.setX(ponto.getX());
						terra.setY(ponto.getY());
						terra.setRaio(30);
						terra.circunferenciaPontoMedio();
						
						Janela.contentPane.desenharPontos(terra.getListaPontos(), Color.GREEN.getRGB());
						try {
							sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		minhaThread.start();
	}
}
