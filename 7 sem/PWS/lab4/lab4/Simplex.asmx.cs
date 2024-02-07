using System.Web.Script.Serialization;
using System.Web.Script.Services;
using System.Web.Services;

namespace lab4
{
    /// <summary>
    /// Сводное описание для Simplex
    /// </summary>
    [WebService(Namespace = "http://BKA/", Description = "The description of the simplex")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Чтобы разрешить вызывать веб-службу из скрипта с помощью ASP.NET AJAX, раскомментируйте следующую строку. 
    [System.Web.Script.Services.ScriptService]
    public class Simplex : System.Web.Services.WebService
    {

        [WebMethod(MessageName = "Add", Description = "Sum of 2 int")]
        public int Add(int x, int y)
        {
            return x + y;
        }

        [WebMethod(MessageName = "Concat", Description = "Concatination of string and double")]
        public string Concat(string s, double d)
        {
            return s + d.ToString();
        }

        [WebMethod(MessageName = "Sum", Description = "Sum of two A objects")]
        public A Sum(A a1, A a2)
        {
            Context.Request.SaveAs("d:\\1POIT\\4\\PWS\\lab4\\RequestS.txt", true);
            return new A(a1.s + a2.s, a1.k + a2.k, a1.f + a2.f);
        }

        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        [WebMethod(MessageName = "AddS", Description = "Sum of 2 int's. Response in JSON")]
        public string AddS(int x, int y)
        {
            int result = x + y;
            return new JavaScriptSerializer().Serialize(result);
        }
    }
}
