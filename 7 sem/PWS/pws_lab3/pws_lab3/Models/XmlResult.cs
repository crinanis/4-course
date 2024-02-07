using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace pws_lab3.Models
{
    public class XmlResult : ActionResult
    {
        public string Content { get; set; }
        public string ContentType { get; set; }

        public override void ExecuteResult(ControllerContext context)
        {
            var response = context.HttpContext.Response;
            response.ContentType = ContentType;
            response.Write(Content);
        }
    }

}