import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * O código em questão é uma aplicação de Agenda básica em Java que permite ao
 * usuário adicionar anotações em uma determinada data, salvar essas anotações
 * em um arquivo de texto e visualizar todas as anotações salvas anteriormente.'
 */

/*
 * A classe Agenda é uma subclasse da classe JFrame e implementa a interface
 * ActionListener que permite tratar eventos de ação gerados pelos componentes
 * da GUI (interface gráfica do usuário).
 */
public class Agenda extends JFrame implements ActionListener {

	private JTextArea areaTexto;
	private JButton botaoSalvar, botaoVisualizar;
	private JComboBox<String> comboDia, comboMes, comboAno;
	private String[] dias, meses, anos;
	private String nomeArquivo = "anotacoes.txt";

	/*
	 * O construtor da classe Agenda inicializa os componentes da GUI, como os
	 * botões, a área de texto e os comboboxes para selecionar o dia, mês e ano da
	 * anotação. Ele também adiciona esses componentes ao contentor da janela usando
	 * um layout BorderLayout e define a janela como visível.
	 */
	public Agenda() {
		super("Agenda");

		dias = new String[31];
		for (int i = 0; i < 31; i++) {
			dias[i] = String.valueOf(i + 1);
		}

		meses = new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };

		anos = new String[10];
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 0; i < 10; i++) {
			anos[i] = String.valueOf(anoAtual + i);
		}

		/*
		 * Os arrays dias, meses e anos são inicializados com valores numéricos e de
		 * texto para preencher os comboboxes com esses valores.
		 */
		JPanel painelData = new JPanel();
		comboDia = new JComboBox<>(dias);
		comboMes = new JComboBox<>(meses);
		comboAno = new JComboBox<>(anos);
		painelData.add(comboDia);
		painelData.add(comboMes);
		painelData.add(comboAno);

		areaTexto = new JTextArea(10, 30);
		JScrollPane painelTexto = new JScrollPane(areaTexto);

		botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(this);

		botaoVisualizar = new JButton("Visualizar anotações");
		botaoVisualizar.addActionListener(this);

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(botaoSalvar);
		painelBotoes.add(botaoVisualizar);

		Container contentor = getContentPane();
		contentor.setLayout(new BorderLayout());
		contentor.add(painelData, BorderLayout.NORTH);
		contentor.add(painelTexto, BorderLayout.CENTER);
		contentor.add(painelBotoes, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * O método actionPerformed() é invocado sempre que um evento de ação é gerado
	 * por um dos componentes da GUI. Esse método verifica qual botão foi clicado
	 * (botão Salvar ou botão Visualizar anotações) e executa a ação correspondente.
	 *
	 * Se o botão Salvar for clicado, o método actionPerformed() recupera os valores
	 * selecionados nos comboboxes (dia, mês e ano), recupera a anotação digitada
	 * pelo usuário na área de texto e salva esses dados em um arquivo de texto. Se
	 * ocorrer um erro ao salvar a anotação, uma mensagem de erro é exibida na saída
	 * padrão (console).
	 * 
	 * Se o botão Visualizar anotações for clicado, o método actionPerformed() lê as
	 * anotações salvas no arquivo de texto e exibe essas anotações na saída padrão
	 * (console). Se ocorrer um erro ao ler as anotações, uma mensagem de erro é
	 * exibida na saída padrão (console).
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botaoSalvar) {
			int dia = Integer.parseInt((String) comboDia.getSelectedItem());
			int mes = comboMes.getSelectedIndex();
			int ano = Integer.parseInt((String) comboAno.getSelectedItem());
			String anotacao = areaTexto.getText();
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
				writer.write(dia + "/" + (mes + 1) + "/" + ano + ": " + anotacao);
				writer.newLine();
				writer.close();
				System.out.println("Anotação salva para " + dia + "/" + (mes + 1) + "/" + ano + ": " + anotacao);
			} catch (IOException ex) {
				System.err.println("Erro ao salvar anotação: " + ex.getMessage());
			}
		} else if (e.getSource() == botaoVisualizar) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
				String linha = null;
				while ((linha = reader.readLine()) != null) {
					System.out.println(linha);
				}
				reader.close();
			} catch (IOException ex) {
				System.out.println("Erro ao escrever no arquivo: " + ex.getMessage());
			}
		}
	}

	/*
	 * O método carregarAnotacoes() é um método auxiliar que carrega as anotações
	 * salvas anteriormente na área de texto da GUI.
	 */
	private void carregarAnotacoes() {
		File arquivo = new File("anotacoes.txt");
		if (arquivo.exists()) {
			try {
				Scanner scanner = new Scanner(arquivo);
				while (scanner.hasNextLine()) {
					String linha = scanner.nextLine();
					areaTexto.append(linha + "\n");
				}
				scanner.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Arquivo não encontrado.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ainda não há anotações salvas.", "Nenhuma anotação",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/*
	 * O método main() simplesmente cria uma nova instância da classe Agenda para
	 * iniciar a aplicação.
	 */
	public static void main(String[] args) {
		new Agenda();
	}
}
