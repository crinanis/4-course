using System;

namespace Client1
{
    internal class Program
    {
        static void Main()
        {
            string binding = "httpEndpoint";
            ServiceReference1.Service1Client simpleClient = new ServiceReference1.Service1Client(binding);
            Console.WriteLine("Method add for ints: " + simpleClient.Add(1, 3));
            Console.WriteLine("Method concat for str and double: " + simpleClient.Concat("str", 3));
            ServiceReference1.A a = simpleClient.Sum(new ServiceReference1.A { f = 3.2f, k = 1, s = "4" }, new ServiceReference1.A { f = 1.3f, k = 2, s = "12" });
            Console.WriteLine($"Result of sum object: f = {a.f} k = {a.k} s = {a.s}");
            simpleClient.Close();
            Console.ReadKey();
        }
    }
}
