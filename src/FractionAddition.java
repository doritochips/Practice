public class FractionAddition {

    public static class Fraction {
        int numerator;
        int denominator;

        Fraction(int n, int d) {
            this.numerator = n;
            this.denominator = d;
        }

        void reduce() {
            int commonGCD = gcd();
            numerator /= commonGCD;
            denominator /= commonGCD;
        }

        private int gcd() {
            int r = Math.max(numerator, denominator);
            int result = 1;

            while(r > 1) {
                if(numerator % r == 0 && denominator % r == 0){
                    result = r;
                    break;
                }
                r --;
            }
            return result;
        }

        void print() {
            System.out.println(String.format("%d / %d", numerator, denominator));
        }
    }

    private static Fraction calculate(Fraction a, Fraction b) {
        Fraction r = new Fraction(a.numerator * b.denominator + a.denominator * b.numerator, a.denominator * b.denominator);
        r.reduce();
        return r;
    }

    public static void main(String[] args) {
        Fraction a = new Fraction(3,5);
        Fraction b = new Fraction(6, 9);

        calculate(a, b).print();
    }
}
