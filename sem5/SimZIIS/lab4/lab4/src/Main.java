
public class Main {
    public static void main(String[] args) {
        DiffiHelmanProtocol DHP = new DiffiHelmanProtocol();
        System.out.println("Дано несекретное число P=2111. Вычислим g - перевообразный корень" +
                "по модулю P. g= " + DHP.getG());

        int a = 2, b = 3;
        long A = DHP.generateSecret(a);
        long B = DHP.generateSecret(b);
        System.out.println("1) Алиса генерирует целое число a и держит его в секрете, затем вычисляет\n" +
                "A = g^(a) mod P и пересылает его Бобу: " + A);
        System.out.println("2) Боб генерирует целое число b и держит его в секрете, затем вычисляет\n" +
                "B = g^(b) mod P и пересылает его Алисе: " + B);

        long Ba = DHP.generateSecret(B,a);
        long Ab = DHP.generateSecret(A,b);
        long gab = DHP.generateSecret(a*b);
        System.out.println("3) Алиса вычисляет значение B^(a) mod P = g^(ab) mod P: " +
                Ba + " = " + gab);

        System.out.println("4) Боб вычисляет значение A^(b) mod P = g^(ab) mod P: " +
                Ab + " = " + gab);


    }
}