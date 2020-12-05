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
        System.out.print("\n�rvore rubro-negra");
        do {
            System.out.print("\n***********************************");
            System.out.print("\nMenu:");
            System.out.print("\n -1: Inserir n�");
            System.out.print("\n -2: Remover n�");
            System.out.print("\n -3: Imprimir �rvore");
            System.out.print("\n -4: Consultar n�");
            System.out.print("\n -5: Exibir Percursos da �rvore");
            System.out.print("\n -0: Sair do programa");
            System.out.print("\n***********************************");
            System.out.print("\nInsira a op��o desejada: ");
            opcao = le.nextInt();
            switch (opcao) {
                case 1: {
                    System.out.println("Informe a quantidade de n�s a serem inseridos: ");
                    a = le.nextInt();
                    for (i = 1; i <= a; i = i + 1) {
                        System.out.print("\nInforme o valor do n� para inserir: ");
                        x = le.nextLong();
                        arv.inserir(x);
                    }
                    break;
                }
                case 2: {
                    System.out.print("\nInforme o valor do n� a ser removido: ");
                    x = le.nextLong();
                    arv.removerNo(x);
                    break;
                }
                case 3: {
                    arv.Impressao();
                    break;
                }
                case 4: {
                    System.out.print("\nInforme o valor do n� que deseja consultar: ");
                    x = le.nextLong();
                    if (x == arv.buscarNo(x).dados) {
                    	System.out.print("\nO n� foi encontrado!");
                    }else {
                    	System.out.print("\nO n� n�o foi encontrado!");
                    }
                    break;
                }
                case 5: {
                    do {
                        System.out.print("\n***********************************");
                        System.out.print("\nOp��es:");
                        System.out.print("\n -1: Pr�-ordem");
                        System.out.print("\n -2: P�s-ordem");
                        System.out.print("\n -3: In-Ordem");
                        System.out.print("\n -0: Voltar");
                        System.out.print("\n***********************************");
                        System.out.print("\nInsira a op��o desejada: ");
                        opcao = le.nextInt();
                        switch (opcao) {
                            case 1: {
                                System.out.print("\nPr�-ordem: ");
                                arv.preOrder();
                                break;
                            }
                            case 2: {
                                System.out.print("\nP�s-ordem: ");
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