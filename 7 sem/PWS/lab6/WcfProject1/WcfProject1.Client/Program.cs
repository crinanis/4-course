using System;

using System.ServiceModel;
using WcfProject1.Common;

namespace WcfProject1.Client
{
    internal class Program
    {
        static void Main(string[] args)
        {


            using (var channelFactory = new ChannelFactory<IPingService>("NetTcpEndpoint"))
            {
                var proxy = channelFactory.CreateChannel();
                string msg = Guid.NewGuid().ToString();
                Console.WriteLine($"Calling '{nameof(IPingService.Ping)}' over TCP with message '{msg}' via ChannelFactory.");
                proxy.Ping(msg);
            }

            using (var channelFactory = new ChannelFactory<IPingService>("NamedPipeEndpoint"))
            {
                var proxy = channelFactory.CreateChannel();
                string msg = Guid.NewGuid().ToString();
                Console.WriteLine($"Calling '{nameof(IPingService.Ping)}' over named pipe with message '{msg}' via ChannelFactory.");
                proxy.Ping(msg);
            }
        }
    }
}
