using SimplexProxy;

namespace SimplexProxy
{
    public class SimplexWeb : SimplexProxy.Simplex
    {
        public override int Add(int x, int y)
        {
            return x + y;
        }

        public override string Concat(string s, double d)
        {
            return s + d;
        }

        public override A Sum(A a1, A a2)
        {
            return new A { s = a1.s + a2.s, k = a1.k + a2.k, f = a1.f + a2.f };
        }

        public override int AddS(int x, int y)
        {
            return x + y;
        }
    }
}