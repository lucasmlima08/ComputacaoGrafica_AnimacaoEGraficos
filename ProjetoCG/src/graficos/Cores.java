package graficos;

import java.awt.Color;

public class Cores {
	private Color cor;
	
	/**
	 * Construtor, recebe o valor das intensidades
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Cores(Color cor){
		this.cor = cor;
	}

	/**
	 * Retorna intensidade vermelha
	 * @return
	 */
	public Color getCor() {
		return cor;
	}
}
