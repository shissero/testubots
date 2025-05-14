import java.util.Scanner;

public class Iniciar {

    public static void main(String[] args){

        IRepositorioCliente repositorioCliente = new IRepositorioCliente();

        repositorioCliente.carregarTodosClientes();

        Historico.construir();

        externo: // TODO: é realmente necessário esse rótulo? Acho que dá para refazer esse código sem isso
        do {
            System.out.println("1 - Listar clientes por maior total em compras"); // TODO: melhor refazer essa parte usando o mesmo método de navegação usado no CRUD de Linguagem de Programação I
            System.out.println("2 - Mostrar o(s) cliente(s) com a(s) maior(es) compra(s) única(s) do ano");
            System.out.println("3 - Listar clientes mais fiéis");
            System.out.println("4 - Recomendar vinho para um cliente");
            System.out.println("5 - Sair");

            Scanner sc =  new Scanner(System.in);

            switch (sc.next().charAt(0)){
                case '1':
                    opcao1();
                    break;

                case '2':

                    opcao2();
                    break;

                case '3':

                    opcao3();
                    break;

                case '4':

                    opcao4();
                    break;

                case '5':
                    break externo;

                default:
                    System.out.println("Opção inválida");
            }
        }while(true);
    }

    static void opcao1(){

        for(Cliente a : Historico.obterClientesPorTotal()){

            System.out.println(a.obterNome() + " (id: " + a.obterID() + ") --> R$ " + a.obterTotalCompras());
        }

        System.out.print("\n\n\n");
    }

    static void opcao2(){

        Scanner sc = new Scanner(System.in);

        externo:
        do {
            System.out.println("Qual ano quer consultar?");
            int ano = Integer.parseInt(sc.next());

            if (Historico.obterMaiorCompraDoAno(ano).isEmpty())
                System.out.println("Nenhuma compra encontrada para este ano.");
            else {
                for (Compra a : Historico.obterMaiorCompraDoAno(ano)) {
                    System.out.println(a.obterCliente().obterNome() + " (id: " + a.obterCliente().obterID() + ")");
                    System.out.println("\tCódigo da compra: " + a.obterCodigo() + "\t\tValor: " + a.obterValorTotal());
                }
            }

            System.out.println("Deseja pesquisar outro ano?(s/n)");

            do {
                switch (sc.next().charAt(0)) {

                    case 's':
                        continue externo;

                    case 'n':
                        return;

                    default:
                        System.out.println("Opção inválida");
                }
            }while(true);
        }while(true);
    }

    static void opcao3(){

        for(Cliente a : Historico.obterClientesMaisFieis()) System.out.println(a.obterNome() + " (id: " + a.obterID() + "): " + a.obterHistorico().size() + " compra(s)");
    }

    static void opcao4(){

        Scanner sc = new Scanner(System.in);

        externo:
        do{
            System.out.println("Digite o CPF do cliente:");

            String cpf = sc.next();

            System.out.println(cpf);

            Cliente cliente = Historico.encontrarClientePorCPF(cpf);

            if(cliente == null){
                System.out.println("Nenhum cliente encontrado");
            }
            else{
                Item item = cliente.sugerirVinho();
                if(item == null) System.out.println("Nada para recomendar");
                else {
                    System.out.println(item.obterProduto() + ", " +
                            item.obterVariedade() + ", " +
                            item.obterVariedade() + ", " +
                            item.obterPais() + ", " +
                            item.obterCategoria() + ", " +
                            item.obterSafra() + ".\n");
                }
            }

            do {
                System.out.println("Recomendar para outro cliente? (s/n)");

                switch (sc.next().charAt(0)) {
                    case 's':
                        continue externo;

                    case 'n':
                        return;

                    default:
                        System.out.println("Opção inválida");
                }
            }while(true);
        }while(true);
    }
}
