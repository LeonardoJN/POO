/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poo_aula_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class POO_aula_1 {

    /*Este é um codigo para a aula introdutoria de POO, ele tem como objetivo simular as 
  funcionalidades de um banco, com cadastro de usuarios, login e operações dentro da area do usuario.
    
  Para as funções eu usei o "CadastraUser" para cadastrar novos usuarios, ele cria o objeto com base
  nas informações solicitadas pelo construtor (nome, cpf, numero e valor) e as armazena em uma ArrayList
  com o nome de "pessoas".
    
  A "AreaDoUsuario" onde são feitas as modificações de um usuario já cadastrado (adicionar e retirar
  valor, mudar o numero e deletar o usuario atual).
    
  "VerificaCpf", "VerificaCell" e "VerificaNome" para verificar se as informações solicitadas já estão 
  cadastradas em algum outro objeto.
    
  "Verifica login" onde irão solicitar o nome e o cpf (chaves para acesso a conta) e caso elas existam e estejam vinculadas
   ira chamar a "AreaDoUsuario".
    
  Dentro da main, são oferecidas opções para cadastrar os usuarios e fazer o login, caso seja escolhido o 1
  o codigo joga a ArrayList "pessoas" para a função "CadastraUser", com a intenção de ter a "base de dados" 
  para comparação.
    
  Caso seja escolhido o 2, ele ira chamar a função "Verifica login" caso as informações solicitadas existam, ele ira retornar 
  "true" e fechar a aba 2 da main, caso "false", irá chamar a função novamente.
    
  Tanto a main como a area do usuario, possuem a opção de "sair", o codigo continuara rodando dentro de um
  while até que o usuario opite pela opção "sair".
    
  Existe também a clase "User", onde são criados e modificados os objetos que serão armazenados na 
  ArrayList. */
    static Scanner s = new Scanner(System.in);

    public static void CadastraUser(ArrayList<User> pessoas) {
        int con = 0, op2, id;
        String cell = null, nome, cpf = null;
        double valor;

        nome = JOptionPane.showInputDialog("insira seu Nome: ");
        op2 = 0;
        while (op2 != 99) {
            con = 0;
            cpf = JOptionPane.showInputDialog("insira seu Cpf: ");
            if (cpf.length() != 11) {
                JOptionPane.showMessageDialog(null, "\ncpf invalido!!\n");
            } else if (cpf.length() == 11) {
                boolean auxBool = VerificaCpf(pessoas, cpf);
                if (auxBool == true) {
                    JOptionPane.showMessageDialog(null, "\nCpf ja cadastrado!!\n");
                } else {
                    op2 = 99;
                }
            }
        }
        while (op2 != 999) {
            cell = JOptionPane.showInputDialog("insira seu Cell: ");
            if (cell.length() != 11) {
                JOptionPane.showMessageDialog(null, "\nNumero invalido!!\n");
            } else if (cell.length() == 11) {
                boolean auxBool = VerificaCell(pessoas, cell);
                if (auxBool == true) {
                    JOptionPane.showMessageDialog(null, "\nNumero ja cadastrado!!\n");
                } else {
                    op2 = 999;
                }
            }
        }
        valor = Double.parseDouble(JOptionPane.showInputDialog("insira seu Valor: "));

        User user = new User(nome, cpf, cell, valor);
        pessoas.add(user);
    }

    public static void AreaDoUsuario(ArrayList<User> pessoas, int i) {

        int op = 0, con = 0, con2 = 0;
        double auxD = 0;
        String auxCell = null;

        JOptionPane.showMessageDialog(null, "\n\n------Bem vindo------\n\n");
        while (op != 99) {
            op = Integer.parseInt(JOptionPane.showInputDialog("\nValor atual: R$" + pessoas.get(i).getValor() + "\n1- Adicionar Valor \n2- Retirar Valor \n3- Alterar Numero \n4- Deletar Usuario \n5- Sair \nOpção: "));
            con = 0;
            con2 = 0;

            switch (op) {
                case 1:
                    auxD = Double.parseDouble(JOptionPane.showInputDialog("\nValor atual: R$" + pessoas.get(i).getValor() + "\nInsira o valor a depositar:"));
                    pessoas.get(i).setValor(auxD + (pessoas.get(i).getValor()));
                    JOptionPane.showMessageDialog(null, "\n\nOperação realizada com sucesso!!\n");
                    break;

                case 2:

                    while (con != 99) {
                        auxD = Double.parseDouble(JOptionPane.showInputDialog("\nValor atual: R$" + pessoas.get(i).getValor() + "\nInsira o valor a retirar:"));
                        if ((pessoas.get(i).getValor() - auxD) < 0) {
                            JOptionPane.showMessageDialog(null, "\nSALDO INSUFICIENTE!!\n\n");
                        } else {
                            con = 99;
                        }
                    }
                    pessoas.get(i).setValor(pessoas.get(i).getValor() - auxD);
                    JOptionPane.showMessageDialog(null, "\n\nOperação realizada com sucesso!!\n");
                    break;

                case 3:

                    JOptionPane.showMessageDialog(null, "\nNumero atual: " + pessoas.get(i).getCell() + "\n");
                    while (con2 != 99) {
                        auxCell = JOptionPane.showInputDialog("Insira o Numero novo");
                        if (auxCell.length() != 11) {
                            JOptionPane.showMessageDialog(null, "\nNumero invalido!!\n");
                        } else if (auxCell.length() == 11) {
                            boolean auxBool = VerificaCell(pessoas, auxCell);
                            if (auxBool == true) {
                                JOptionPane.showMessageDialog(null, "\nNumero ja cadastrado!!\n");
                            } else {
                                con2 = 99;
                            }
                        }
                    }
                    pessoas.get(i).setCell(auxCell);
                    JOptionPane.showMessageDialog(null, "\nNumero cadastrado com sucesso!! \nSeu novo numero é: " + pessoas.get(i).getCell() + "\n");
                    break;

                case 4:
                    String S_N = JOptionPane.showInputDialog("Deseja deletar o Usuario? ([S]sim /[N]não) \n\nOpção: ");
                    if ("S".equals(S_N) || "s".equals(S_N)) {
                        pessoas.remove(i);
                        JOptionPane.showMessageDialog(null, "\nUsuario deletado com sucesso!!\nA Pagina Do Usuario sera fechada agora, Adeus!");
                        op = 99;
                    } else {
                        JOptionPane.showMessageDialog(null, "\nOperação cancelada");
                    }
                    break;

                case 5:

                    op = 99;
                    break;
            }
        }
    }

    public static boolean VerificaCpf(ArrayList<User> pessoas, String cpf) {

        int op2 = 0;

        for (int con = 0; con < pessoas.size(); con++) {
            if (pessoas.get(con).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public static boolean VerificaCell(ArrayList<User> pessoas, String cell) {

        int op2 = 0;

        for (int con = 0; con < pessoas.size(); con++) {
            if (pessoas.get(con).getCell().equals(cell)) {
                return true;
            }
        }
        return false;
    }

    public static boolean VerificaNome(ArrayList<User> pessoas, String nome) {

        int op2 = 0;

        for (int con = 0; con < pessoas.size(); con++) {
            if (pessoas.get(con).getCpf().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public static boolean VerificaLogin(ArrayList<User> pessoas) {
        String nome = JOptionPane.showInputDialog("insira seu Nome: ");
        String cpf = JOptionPane.showInputDialog("insira seu Cpf: ");
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getNome().equals(nome) && pessoas.get(i).getCpf().equals(cpf)) {
                AreaDoUsuario(pessoas, i);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        ArrayList<User> pessoas = new ArrayList();
        int op = 0, id, con = 0;
        String nome, cell = null, cpf = null;

        double valor;

        JOptionPane.showMessageDialog(null, "----------BEM VINDO AO BANCO POO----------\n");

        while (op != 3) {
            op = Integer.parseInt(JOptionPane.showInputDialog("\n1- Cadastro \n2- Login \n3- Sair \n\nOpção: "));
            switch (op) {

                case 1:
                    CadastraUser(pessoas);
                    break;

                case 2:
                    while (op != 99) {
                        boolean auxBool = VerificaLogin(pessoas);
                        if (auxBool == false) {
                            JOptionPane.showMessageDialog(null, "\nNumero ou Cpf Invalidos!!\n");
                        } else {
                            op = 99;
                        }
                    }

                    break;

                case 3:
                    op = 3;

                    break;
            }
        }
    }
}
