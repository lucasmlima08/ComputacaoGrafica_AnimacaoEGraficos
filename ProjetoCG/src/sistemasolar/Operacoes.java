package sistemasolar;

import java.util.ArrayList; 
import java.util.List;

public class Operacoes {

	/**
	 * Matriz geradora de transla��o em uma matriz de 2D (duas dimen��es).
	 * @param tx
	 * @param ty
	 * @return vetor 2D (3 x 3) Double
	 */
	private double[][] gerarMatrizTranslacao(int tx, int ty) {
		double[][] matriz = new double[3][3];

		matriz[0][0] = 1;
		matriz[0][1] = 0;
		matriz[0][2] = tx;

		matriz[1][0] = 0;
		matriz[1][1] = 1;
		matriz[1][2] = ty;

		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	/**
	 * Matriz geradora de escala em uma matriz de 2D (duas dimensões).
	 * @param sx
	 * @param sy
	 * @return
	 */
	private double[][] gerarMatrizEscala(double sx, double sy) {
		double[][] matriz = new double[3][3];
		if (sx == 0) {
			sx = 1;
		}
		if (sy == 0) {
			sy = 1;
		}
		matriz[0][0] = sx;
		matriz[1][0] = 0;
		matriz[2][0] = 0;

		matriz[0][1] = 0;
		matriz[1][1] = sy;
		matriz[2][1] = 0;

		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	/**
	 * Matriz de rota��o em uma matriz de 2D (duas dimen��es)
	 * @param angulo
	 * @return vetor 3 x 3 (double)
	 */
	private double[][] gerarMatrizRotacao(double angulo) {

		double[][] matriz2D = new double[3][3];

		double sen = Math.sin(Math.toRadians(angulo));
		double cos = Math.cos(Math.toRadians(angulo));

		
		// Coluna 0
		matriz2D[0][0] = cos;
		matriz2D[1][0] = sen;
		matriz2D[2][0] = 0;
		// Coluna 1
		matriz2D[0][1] = -sen;
		matriz2D[1][1] = cos;
		matriz2D[2][1] = 0;
		// Coluna 2
		matriz2D[0][2] = 0;
		matriz2D[1][2] = 0;
		matriz2D[2][2] = 1;

		return matriz2D;
	}

	private double[][] gerarMatrizReflexaoX() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = -1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	private double[][] gerarMatrizReflexaoY() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = -1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	private double[][] gerarMatrizReflexaoXY() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = -1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = -1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	/**
	 * Matriz de cisalhamento
	 * 	1	a 	0
	 * 	b	1 	0
	 * 	0 	0	1
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double[][] gerarMatrizCisalhamento(Double a, Double b) {

		double[][] matriz = new double[3][3];
		
		// Coluna 0
		matriz[0][0] = 1;
		matriz[1][0] = b;
		matriz[2][0] = 0;
		// Coluna 1
		matriz[0][1] = a;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		
		return matriz;
	}

	// Opera��es b�sicas
	private double[][] translacaoMulti(double[][] matriz, int x, int y) {

		try {
			double[][] d = CalculoMatrizes.multiplicaMatrizes(
					gerarMatrizTranslacao(x, y), matriz);

			return d;
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLA��O");
		}
		return matriz;
	}

	public List<Ponto> translacaoMulti(List<Ponto> objeto, int x, int y) {
		List<Ponto> list = new ArrayList<Ponto>();

		double[][] matriz = new double[3][objeto.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < objeto.size(); i++) {
			matriz[0][i] = objeto.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = objeto.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna i na linha 2 = 1
		}

		double[][] d = null;
		try {
			d = CalculoMatrizes.multiplicaMatrizes(gerarMatrizTranslacao(x, y), matriz);
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLA��O");
		}
		
		for (int i = 0; i < d[0].length; i++) {
			list.add(new Ponto((int) d[0][i], (int) d[1][i], (int) d[2][i]));
		}
//		System.out.println(list.toString() + "a\n");
		return list;
	}

	public List<Ponto> escalaReta(List<Ponto> objeto, double x, double y) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][objeto.size() + 1];

		// Criando o objeto de matriz
		for (int i = 0; i < objeto.size(); i++) {
			matriz[0][i] = objeto.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = objeto.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna i na linha 2 = 1
		}

		int translacaox = objeto.get(0).getX();
		int translacaoy = objeto.get(0).getY();

		// Fazer a transla��o do objeto
		double[][] matrizNaOrigem = translacaoMulti(matriz, -translacaox,
				-translacaoy);

		// Matriz da escala.
		double[][] escala = gerarMatrizEscala(x, y);

		// Gerando a matriz da escala
		double[][] a = null;
		try {
			new CalculoMatrizes();
			a = CalculoMatrizes.multiplicaMatrizes(escala, matrizNaOrigem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Voltar a reta a posi��o de origem
		double[][] b = translacaoMulti(a, translacaox, translacaoy);

		for (int i = 0; i < b[0].length; i++) {
			list.add(new Ponto((int) b[0][i], (int) b[1][i], (int) b[2][i]));
		}
		return list;
	}

	/**
	 * 
	 * @param angulo
	 * @return List<Ponto>, objeto rotacionado.
	 */
	/*public List<Ponto> rotacao(int angulo) {
	
		// Pegar a lista de pontos da TelaPrincipal
		List<Ponto> lis = TelaPrincipal.getLista();
		
		double[][] matriz = new double[3][lis.size()];

		// Criando o objeto de matriz da lista.
		for (int i = 0; i < lis.size(); i++) {
			matriz[0][i] = lis.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lis.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}
				
		// Ponto de origem.
		final int translacaoX = lis.get(0).getX();
		final int translacaoY = lis.get(0).getY();
		
		// Fazer a transla��o.
		List<Ponto> matrix_trans = translacaoMulti(lis, -translacaoX, -translacaoY);
		
		double[][] matrizNaOrigem = new double[3][lis.size()];

		for (int i = 0; i < matrix_trans.size(); i++) {
			matrizNaOrigem[0][i] = (double) matrix_trans.get(i).getX();
			matrizNaOrigem[1][i] = (double) matrix_trans.get(i).getY();
			matrizNaOrigem[2][i] = (double) matrix_trans.get(i).getZ();
		}
		
		// Gerar a matriz de rota��o
		double[][] rotacao = gerarMatrizRotacao(angulo);

		// Fazer a rota��o
		double[][] lisPonto = null;
		try {
			lisPonto = Matriz.multiplicaMatrizes(rotacao, matrizNaOrigem);			
		} catch (Exception e) {
			System.err.println("Erro ao multiplicar a matriz de rota��o");
			e.printStackTrace();
		}

		double[][] matrizVoltar = translacaoMulti(lisPonto, translacaoX, translacaoY);
		
		List<Ponto> ls = new ArrayList<Ponto>();
		for (int i = 0; i < matrizVoltar[0].length; i++) {
			ls.add(
					new Ponto(
							(int) Math.round(matrizVoltar[0][i])
						, (int) Math.round(matrizVoltar[1][i])
						,(int) Math.round(matrizVoltar[2][i])
							));
		}
		
		
		return ls;
	}*/

	// Reflexao
	public List<Ponto> reflexaoX(List<Ponto> lstPontos) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][lstPontos.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lstPontos.size(); i++) {
			matriz[0][i] = lstPontos.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lstPontos.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoX();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = CalculoMatrizes.multiplicaMatrizes(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex�o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i]));

		}

		return list;
	}

	public List<Ponto> reflexaoY(List<Ponto> lstPontos) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][lstPontos.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lstPontos.size(); i++) {
			matriz[0][i] = lstPontos.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lstPontos.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoY();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = CalculoMatrizes.multiplicaMatrizes(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex\u00E3o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i]));

		}

		return list;
	}

	public List<Ponto> reflexaoXY(List<Ponto> lista) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoXY();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = CalculoMatrizes.multiplicaMatrizes(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex\u00E3o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i]));
		}

		return list;
	}

	public List<Ponto> cisalhamentoEmX(List<Ponto> lista, Double a, Double b) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] cisalhamento = gerarMatrizCisalhamento(a, b);

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = CalculoMatrizes.multiplicaMatrizes(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i]));

		}

		return list;
	}

	public List<Ponto> cisalhamentoEmY(List<Ponto> lista, Double a, Double b) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[3][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] cisalhamento = gerarMatrizCisalhamento(a, b);

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = CalculoMatrizes.multiplicaMatrizes(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i]));

		}

		return list;
	}

	public List<Ponto> cisalhamentoEmZ(List<Ponto> lista, Double a, Double b) {
		
		List<Ponto> list = new ArrayList<Ponto>();

		double[][] matriz = new double[3][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = 1; // Coluna j na linha 2 = 1
		}

		
		double[][] cisalhamento = gerarMatrizCisalhamento(a, 0.0);

		double[][] matriz_cizalhamento = null;
		try {
			matriz_cizalhamento = CalculoMatrizes.multiplicaMatrizes(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}

		
		
		// y
		double[][] cisalhamentoy = gerarMatrizCisalhamento(0.0, b);

		double[][] matriz_cizalhamentoy = null;
		try {
			matriz_cizalhamentoy = CalculoMatrizes.multiplicaMatrizes(cisalhamentoy, matriz_cizalhamento);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}
		
		list.clear();
		for (int i = 0; i < matriz_cizalhamentoy[0].length; i++) {
			list.add(
					new Ponto(
							(int) Math.round(matriz_cizalhamentoy[0][i]*100)/100,
							(int) Math.round(matriz_cizalhamentoy[1][i]*100)/100, 
					(int) Math.round(matriz_cizalhamentoy[2][i]*100)/100)
			);

		}

		return list;
	}
}
