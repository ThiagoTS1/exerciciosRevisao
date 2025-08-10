import java.util.Scanner;

public class exercicio01 {
    public static void main(String[] args) {
        // Cria o objeto Scanner para ler dados do usuário
        Scanner leitor = new Scanner(System.in);
        
        // Declaração das variáveis
        double nota1, nota2, nota3, media;
        
        // Entrada de dados
        System.out.println("=== Calculadora de Média ===");
        System.out.print("Digite a primeira nota: ");
        nota1 = leitor.nextDouble();
        
        System.out.print("Digite a segunda nota: ");
        nota2 = leitor.nextDouble();
        
        System.out.print("Digite a terceira nota: ");
        nota3 = leitor.nextDouble();
        
        // Cálculo da média aritmética
        media = (nota1 + nota2 + nota3) / 3.0;
        
        // Exibição da média
        System.out.printf("\nMédia do aluno: %.2f\n", media);
        
        // Estrutura if-else para determinar a situação do aluno
        if (media >= 7.0) {
            System.out.println("Situação: Aprovado");
        } else if (media >= 5.0) {
            System.out.println("Situação: Recuperação");
        } else {
            System.out.println("Situação: Reprovado");
        }
        
        // Fecha o Scanner
        leitor.close();
    }
}
