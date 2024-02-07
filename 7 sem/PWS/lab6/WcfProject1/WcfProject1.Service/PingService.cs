using System;
using WcfProject1.Common;


namespace WcfProject1.Service
{
    internal class PingService : IPingService
    {


        public void Ping(string message)
        {
            Console.WriteLine($"Operation '{nameof(Ping)}' was called with argument '{message}'.");
        }
    }
}