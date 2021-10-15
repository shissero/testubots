# testubots

As três linguagens de programação nas quais me senti 100% seguro para resolver este desafio são C, C++ e Java. C é a que tenho mais desenvoltura – e que gosto mais, diga-se de passagem –, mas o fato de não possuir orientação a objetos a torna uma candidata fraca para esse projeto. Eu tenho certeza que usando structs poderia simular a implementação das classes necessárias, mas ainda assim seria lento, e eu teria que fazer algumas coisas do zero. O C++ é muito “burocrático” de codar, muitos detalhes com os quais se preocupar, sem contar que não disponho de nenhuma IDE para programar em C++. Tudo isso faria um projeto simples se tornar extremamente penoso e eu com certeza não conseguiria terminar em quatro dias. Então, escolhi o Java. Tenho uma IDE ótima para essa linguagem – IntelliJIDEA –  e ela é simples de lidar, sem ter todos os problemas que C++ cria e sem as limitações de C.

A primeira medida que tomei foi salvar em arquivos de texto o histórico de compras e a lista de clientes que me foram passadas, para o programa ler e extrair as informações. Em seguida, criei as classes óbvias: uma para representar os clientes, uma para as compras e uma terceira para os itens. Depois, criei uma classe para lidar com o histórico de compras. Essa classe é responsável por ler o arquivo do histórico e armazenar as informações para que o programa possa usá-las mais tarde. Eu declarei o construtor dessa classe como private para escondê-lo das outras classes, já que não haverá objetos dessa classe.

Eu gostaria de ter simplificado a conversão dos dados de strings lidas dos arquivos de texto para os formatos de dado adequado, mas não consegui pensar num método simples de fazer. Não fiquei satisfeito, as funções ficaram muito grandes e difíceis de ler e entender. Mas funcionam, pelo menos.

A classe histórico armazena uma lista de objetos de cada classe criada, que são preenchidas através do método build()  dessa classe assim que o programa se inicia. Criei métodos para, em separado, finalizar o preenchimento das informações necessárias que não dependiam diretamente do arquivo de texto, como as listas de vinhos comprados e não comprados de cada cliente, por exemplo. Assim, o método build() ficou bem limpo e claro de se entender.

Para a primeira tarefa, usei o quicksort para ordenar os dados na ordem correta. Ele é simples e bastante eficiente.

Para a segunda, decidi dar ao usuário a liberdade de pesquisar por qualquer ano possível. O método de pesquisa que a função implementada para esta tarefa usa é o de pesquisa sequencial. Me dei a esse luxo por ser um volume pequeno de dados, mas num volume muito maior, reordenaria os dados para usar pesquisa binária – obviamente que num volume tão grande de dados eu usaria C ou C++, que me permitem controlar melhor o uso de memória.

Para a terceira, novamente, pesquisa sequencial. O programa seleciona os clientes mais fiéis através do tamanho de seus históricos de compras, que são armazenados no momento em que o programa está lendo lendo o arquivo com o histórico das compras da loja.

Para a última tarefa, usei o método descrito em https://www.toptal.com/algorithms/predicting-likes-inside-a-simple-recommendation-engine. É um método bastante simples. Pelo volume pequeno de dados, usei os vinhos comprados e os vinhos não comprados, por simplicidade. Eu pensei em usar vinhos que o cliente gostou e os que não gostou, e colocar na lista dos que ele gostou somente aqueles que foram comprados mais de uma vez, mas também pensei que seria desnecessariamente complicado para esse desafio.

Por último, mas não menos importante, a classe Conjunto, que possui métodos para lidar com as operações de conjuntos das quais o programa necessita, o que me ajudou muito a deixar as funções simples e claras.
