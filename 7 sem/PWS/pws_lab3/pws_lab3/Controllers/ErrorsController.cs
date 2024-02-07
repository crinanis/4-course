using System;
using System.Web.Http;
using System.Net.Http;
using System.Net;
using PWS_3.Models;

namespace PWS_3.Controllers
{
    public class ErrorsController : ApiController
    {
        [HttpGet]
        [Route("api/errors/{code}")]
        public object GetErrors(int code, HttpRequestMessage request)
        {
            string format = request.Content.Headers.ContentType?.MediaType;
            var result = new ErrorDto(code, GetErrorMessage(code));
            if (format == "application/xml")
            {
                return Content(HttpStatusCode.OK, result, Configuration.Formatters.XmlFormatter);
            }
            else
            {
                return Json(result);
            }
        }

        public static string GetErrorMessage(int code)
        {
            switch (code)
            {
                case 500:
                    return "Server Error";
                case 501:
                    return "Invalid format";
                case 400:
                    return "Invalid parameters";
                default:
                    return "invalid error code";
            }
        }
    }
}