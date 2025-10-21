package cphelper;

import java.math.BigInteger;

public final class MathUtils {
    private MathUtils() {}

    // gcd (Euclidean) - O(log(min(a,b)))
    public static long gcd(long a, long b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    // lcm using gcd - watch overflow externally
    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a / gcd(a, b) * b);
    }

    // safe powMod: (base^exp) % mod, O(log exp)
    public static long powMod(long base, long exp, long mod) {
        base %= mod;
        if (base < 0) base += mod;
        long res = 1 % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = mulMod(res, base, mod);
            base = mulMod(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    // safe mulMod: returns (a*b) % mod without wrong overflow
    // Simple approach using BigInteger for clarity (slower but safe)
    public static long mulMod(long a, long b, long mod) {
        // normalize
        a %= mod; if (a < 0) a += mod;
        b %= mod; if (b < 0) b += mod;
        // if mod < 2^62 and a*b fits in long, we can do plain multiply;
        // but for absolute safety, use BigInteger (clear to explain in interview)
        BigInteger big = BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod));
        return big.longValue();
    }

    // modular inverse for prime mod using Fermat: a^(mod-2) % mod
    // explain: only valid when mod is prime
    public static long modInversePrime(long a, long mod) {
        return powMod(a, mod - 2, mod);
    }

    // simple deterministic isPrime for n <= 2e9 (trial up to sqrt(n))
    // For larger n you'd use Miller-Rabin (not shown here for simplicity)
    public static boolean isPrimeSimple(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (long i = 3; i * i <= n; i += 2) if (n % i == 0) return false;
        return true;
    }
}

