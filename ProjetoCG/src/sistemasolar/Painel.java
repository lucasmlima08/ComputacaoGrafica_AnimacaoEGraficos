package sistemasolar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class Painel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	BufferedImage ImageViewPort;
	
	/**
	 * Create the panel.
	 */
	public Painel() {
		
		setBounds(450, 25, 600, 600);
		ImageViewPort = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
		limpaPainel();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		
		g.drawImage((Image)ImageViewPort, 0, 0, null);
		g.drawLine(300, 0, 300, 600); //x
		g.drawLine(0, 300, 600, 300);
		
	}
	
	public void limpaPainel(){
		for(int i=0; i<600; i++){
			for(int j=0; j<600; j++){
				ImageViewPort.setRGB(i, j, Color.WHITE.getRGB() );
			}
		}
		repaint();
	}
	
	public void setPixel(int x, int y, int cor){
		ImageViewPort.setRGB(x+300, 300-y, cor);
		repaint();
		
	}
	
	public void desenharPontos(List<Ponto> pontos, int cor) {
		for (Ponto ponto : pontos) {
			try {
				setPixel(ponto.getX(), ponto.getY(), cor);
				
			} catch (Exception e) {}
		}
		repaint();
	
	}
}


