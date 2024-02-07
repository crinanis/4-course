namespace lab5
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени класса "Service1" в коде и файле конфигурации.
    public class Service1 : IService1
    {
        public int Add(int x, int y) => x + y;
        public string Concat(string s, double d) => s + d;
        public A Sum(A a1, A a2) => new A(a1.s + a2.s, a1.k + a2.k, a1.f + a2.f);
    }
}
