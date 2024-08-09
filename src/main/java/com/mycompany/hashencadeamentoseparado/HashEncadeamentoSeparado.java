package com.mycompany.hashencadeamentoseparado;
import java.util.*;

public class HashEncadeamentoSeparado<T> {
    
    public static class Dado<T>{
        long chave;
        T valor;
        Dado(long chave, T valor){
            this.chave = chave;
            this.valor = valor;
        }
    }
    
    // declarando a tabela hash
    
    private LinkedList<Dado<T>> [] tabela;
    private int tamanho;
    private int numeroElementos;
    
    public HashEncadeamentoSeparado(int tamanho){
        this.tamanho = tamanho;
        tabela = new LinkedList[this.tamanho];
        for(int i = 0; i < this.tamanho; i++){
            tabela[i] = new LinkedList<Dado<T>>();
        }
    
    }
    
    private int funcaoHash(long chave){
        return (int)(chave%this.tamanho);
    }
    
    public void put(long chave, T valor){
        Dado<T> novoDado = new Dado<>(chave,valor);
        int indice = funcaoHash(chave);
        tabela[indice].add(novoDado);
    }
    
    public Dado<T> containsKey(long chave){
        
        int indice = funcaoHash(chave);
        for(Dado<T> dado : tabela[indice]){
            if(chave == dado.chave){
                return dado;
            }
        } return null;
    }
    
    public void printHash() {
        for (int i = 0; i < tamanho; i++) {
        	System.out.print("[" + i + "] ");
        	for (Dado<T> item : tabela[i]) {
            	System.out.print(" -> " + item.chave + ":" + item.valor);
        	}
        	System.out.println();
        }
    }
    
    
    private static int menu(Scanner scanner) {
        System.out.println("\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH SEPARADO ***");
        System.out.println("1-Inserir");
        System.out.println("2-Remover");
        System.out.println("3-Buscar");
        System.out.println("4-Alterar");
        System.out.println("0-Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        System.out.print("Tamanho da tabela:");
        int n = leia.nextInt();
        
        HashEncadeamentoSeparado<String> tabelaHash = new HashEncadeamentoSeparado<>(n);
        
        int op;
        do{
            tabelaHash.printHash();
            op = menu(leia);
            switch(op){
                case 1:
                    System.out.print("Entre com a chave:");
                    long chave = leia.nextLong();
                    leia.nextLine(); //limpa buffer
                    System.out.print("Objeto[Nome]:");
                    String nome = leia.nextLine();
                    tabelaHash.put(chave, nome);
                    break;
                    
                case 3:
                    System.out.print("Entre com a chave:");
                    chave = leia.nextLong();
                    leia.nextLine(); //limpa buffer
                    Dado<String> dadoRetorno = tabelaHash.containsKey(chave);
                    if(dadoRetorno == null){
                        System.out.println("O Objeto não existe.");
                    }else{
                        System.out.println("Objeto retornado:" + dadoRetorno.valor);
                    }
                    
                    break;
                case 0:
                    System.out.println("Saindo...");
            }
        }while(op != 0);
        
    }
}
