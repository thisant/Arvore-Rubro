package br.com.arvore_rubro_negra;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner le = new Scanner(System.in);
        Arvore arv = new Arvore();
        int opcao;
        int i;
        int a = 0;
        long x;
        System.out.print("\nÁrvore rubro-negra");
        do {
            System.out.print("\n***********************************");
            System.out.print("\nMenu:");
            System.out.print("\n -1: Inserir nó");
            System.out.print("\n -2: Remover nó");
            System.out.print("\n -3: Imprimir árvore");
            System.out.print("\n -4: Consultar nó");
            System.out.print("\n -5: Exibir Percursos da árvore");
            System.out.print("\n -0: Sair do programa");
            System.out.print("\n***********************************");
            System.out.print("\nInsira a opção desejada: ");
            opcao = le.nextInt();
            switch (opcao) {
                case 1: {
                    System.out.println("Informe a quantidade de nós a serem inseridos: ");
                    a = le.nextInt();
                    for (i = 1; i <= a; i = i + 1) {
                        System.out.print("\nInforme o valor do nó para inserir: ");
                        x = le.nextLong();
                        arv.inserir(x);
                    }
                    break;
                }
                case 2: {
                    System.out.print("\nInforme o valor do nó a ser removido: ");
                    x = le.nextLong();
                    arv.removerNo(x);
                    break;
                }
                case 3: {
                    arv.Impressao();
                    break;
                }
                case 4: {
                    System.out.print("\nInforme o valor do nó que deseja consultar: ");
                    x = le.nextLong();
                    if (x == arv.buscarNo(x).dados) {
                    	System.out.print("\nO nó foi encontrado!");
                    }else {
                    	System.out.print("\nO nó não foi encontrado!");
                    }
                    break;
                }
                case 5: {
                    do {
                        System.out.print("\n***********************************");
                        System.out.print("\nOpções:");
                        System.out.print("\n -1: Pré-ordem");
                        System.out.print("\n -2: Pós-ordem");
                        System.out.print("\n -3: In-Ordem");
                        System.out.print("\n -0: Voltar");
                        System.out.print("\n***********************************");
                        System.out.print("\nInsira a opção desejada: ");
                        opcao = le.nextInt();
                        switch (opcao) {
                            case 1: {
                                System.out.print("\nPré-ordem: ");
                                arv.preOrder();
                                break;
                            }
                            case 2: {
                                System.out.print("\nPós-ordem: ");
                                arv.postOrder();
                                break;
                            }
                            case 3: {
                                System.out.print("\nIn-ordem: ");
                                arv.inOrder();
                                break;
                            }
                        }
                    } while (opcao != 0);
                }
            }
        } while (opcao != 0);

    }
}