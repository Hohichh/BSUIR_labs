public class DiffiHelmanProtocol {
    private final long P = 2111;
    private long g;

    public DiffiHelmanProtocol() {
        this.g = (int) generateGFP(); // Найдем первообразный корень g при инициализации
    }

    public long generateSecret(long x){
        return powMod(g,x,P);
    };
    public long generateSecret(long base, long x){
        return powMod(base,x,P);
    };

    // Функция для возведения числа в степень по модулю P
    private long powMod(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    // Проверка, является ли число g примитивным корнем по модулю P
    private boolean isPrimitiveRoot(int g) {
        // Разложение P-1 = 2110 на простые множители: 2, 5, 211
        int[] factors = {2, 5, 211};

        // Проверим для всех делителей P-1
        for (int factor : factors) {
            if (powMod(g, (P - 1) / factor, P) == 1) {
                return false;
            }
        }
        return true;
    }

    // Метод для нахождения примитивного корня g по модулю P
    private long generateGFP() {
        for (int g = 2; g < P; g++) {
            if (isPrimitiveRoot(g)) {
                return g;
            }
        }
        return -1; // Если не найдено (что маловероятно для простого P)
    }

    public long getG() {
        return g;
    }
}
