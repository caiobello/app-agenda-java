# AgendaJava
Agenda de anotações Java


O código apresentado é uma aplicação simples de Agenda/Diário em Java que permite ao usuário adicionar anotações em uma determinada data, salvar essas anotações em um arquivo de texto e visualizar todas as anotações salvas anteriormente.

A classe Agenda é uma subclasse da classe JFrame e implementa a interface ActionListener que permite tratar eventos de ação gerados pelos componentes da GUI (interface gráfica do usuário).

O construtor da classe Agenda inicializa os componentes da GUI, como os botões, a área de texto e os comboboxes para selecionar o dia, mês e ano da anotação. Ele também adiciona esses componentes ao contentor da janela usando um layout BorderLayout e define a janela como visível.

O método actionPerformed() é invocado sempre que um evento de ação é gerado por um dos componentes da GUI. Esse método verifica qual botão foi clicado (botão 'Salvar' ou botão 'Visualizar' anotações) e executa a ação correspondente.

Se o botão 'Salvar' for clicado, o método actionPerformed() recupera os valores selecionados nos comboboxes (dia, mês e ano), recupera a anotação digitada pelo usuário na área de texto e salva esses dados em um arquivo de texto. Se ocorrer um erro ao salvar a anotação, uma mensagem de erro é exibida na saída padrão (console).

Se o botão 'Visualizar' anotações for clicado, o método actionPerformed() lê as anotações salvas no arquivo de texto e exibe essas anotações na saída padrão (console). Se ocorrer um erro ao ler as anotações, uma mensagem de erro é exibida na saída padrão (console).

# Dev. Caio Bello
