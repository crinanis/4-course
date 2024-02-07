using System.ServiceModel;

namespace WcfProject1.Common
{
    [ServiceContract]
    public interface IPingService
    {
        [OperationContract]
        void Ping(string message);
    }

}
