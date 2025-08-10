import java.util.Scanner;

public class exercicio02 {
    public static void main(String[] args) {
        // Cria o objeto Scanner para ler dados do usuário
        Scanner leitor = new Scanner(System.in);
        
        // Declaração da variável
        int numero;
        
        // Entrada de dados
        System.out.println("=== Gerador de Tabuada ===");
        System.out.print("Digite um número inteiro: ");
        numero = leitor.nextInt();
        
        // Exibição do cabeçalho
        System.out.println("\nTabuada do " + numero + ":");
        System.out.println("------------------------");
        
        // Laço for para calcular e exibir a tabuada
        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.println(numero + " x " + i + " = " + resultado);
        }
        
        // Fecha o Scanner
        leitor.close();
    }
} 