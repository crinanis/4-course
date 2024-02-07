using lab5;
using System;
using System.ServiceModel;

namespace Host
{
    internal class Program
    {
        static void Main()
        {
            var host = new ServiceHost(typeof(Service1));
            host.Open();
            Console.WriteLine("Service has been started");
            Console.ReadLine();
        }
    }
}
