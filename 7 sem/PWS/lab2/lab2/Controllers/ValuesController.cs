using System.Web.Http;

namespace lab2.Controllers
{
    public class ValuesController : ApiController
    {

        [HttpGet]
        public string Get()
        {
            return Models.Data.Get();
        }

        [HttpPost]
        public string Post([FromBody] string result)
        {
            int number;
            if (int.TryParse(result, out number))
            {
                Models.Data.Post(number);

                return Get();
            }

            return Get();
        }

        [HttpPut]
        public string Put([FromBody] string add)
        {
            int number;
            if (int.TryParse(add, out number))
            {
                Models.Data.Put(number);
            }

            return Get();
        }


        [HttpDelete]
        public string Delete()
        {
            Models.Data.Delete();
            return Get();
        }
    }
}
