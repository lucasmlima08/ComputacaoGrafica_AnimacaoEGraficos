package sistemasolar;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Painel contentPane;

	public Janela() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(450, 25, 614, 639);
		contentPane = new Painel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	}
}



