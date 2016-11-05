package graficos;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaGraficos extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public JanelaGraficos() {
		setTitle("Gráficos");
		panel = new Painel();
		add(panel);
		setSize(880, 610);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
