using System.ServiceModel;

namespace lab6
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени интерфейса "IService" в коде и файле конфигурации.
    [ServiceContract]
    public interface IService
    {
        [OperationContract]
        void DoWork();
    }
}
