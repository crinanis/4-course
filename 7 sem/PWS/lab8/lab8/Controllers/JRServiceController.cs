using lab8.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace lab8.Controllers
{
    public class JRServiceController : Controller
    {
        private static bool ignoreMethods = false;
        private static string JSONVersion = "2.0";
        public ActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public void ContinueRequests()
        {
            ignoreMethods = false;
        }

        [HttpPost]
        public JsonResult Multi(ReqJsonRPC[] body)
        {
            try
            {
                string contentType = Request.ContentType;
                if (string.IsNullOrEmpty(contentType) || !contentType.Contains("application/json"))
                {
                    return Json(new JRPCError(body[0].Id, JSONVersion, JRPCErrorCodes.ServerErrorStart, "Server error"));
                }

                HashSet<string> usedIds = new HashSet<string>();

                int length = body.Length;
                JsonResult[] result = new JsonResult[length];

                for (int i = 0; i < length; i++)
                {
                    if (!usedIds.Add(body[i].Id))
                    {
                        return Json(new JRPCError(body[i].Id, JSONVersion, JRPCErrorCodes.InvalidRequest, "Duplicate id within the batch."));
                    }

                    result[i] = Single(body[i]);

                    //if (result[i].Data is JRPCError)
                    //{
                    //    return Json(new JRPCError(body[i].Id, JSONVersion, JRPCErrorCodes.ServerErrorStart, "Server error"));
                    //}
                }

                ignoreMethods = false;

                return Json(result.Select(r => r.Data));
            }
            catch (Exception ex)
            {
                return Json(new JRPCError("1", JSONVersion, JRPCErrorCodes.InternalError, $"Internal server error: {ex.Message}"));
            }
        }

        [HttpPost]
        public JsonResult Single(ReqJsonRPC body)
        {
            if (string.IsNullOrEmpty(body.Id))
            {
                return Json(null);
            }

            string contentType = Request.ContentType;
            if (string.IsNullOrEmpty(contentType) || !contentType.Contains("application/json"))
            {
                return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.ServerErrorStart, "Server error"));
            }

            string method = body.Method;
            ParamsData parameters = body.Params;
            string jsonrpc = body.Jsonrpc;

            if (ignoreMethods) return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.InvalidRequest, "Invalid request"));
            if (jsonrpc == null || jsonrpc != "2.0") return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.ServerErrorEnd, "Server error"));

            if (parameters == null)
            {
                if (body.PositionalParams != null && body.PositionalParams.Count == 2)
                {
                    parameters = new ParamsData
                    {
                        Key = body.PositionalParams[0].ToString(),
                        Value = body.PositionalParams[1].ToString()
                    };
                }
                else
                {
                    return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.InvalidParams, "Invalid params"));
                }
            }

            if (parameters == null || parameters.Key == null || parameters.Value == null) return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.InvalidParams, "Invalid params"));

            int? result = null;

            string key = parameters.Key;

            string valueStr = parameters.Value;

            if (!int.TryParse(valueStr, out int value))
            {
                return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.ParseError, "Parse error"));
            }
            try
            {
                switch (method)
                {
                    case "SetM": { result = SetM(key, value); break; }
                    case "GetM": { result = GetM(key); break; }
                    case "AddM": { result = AddM(key, value); break; }
                    case "SubM": { result = SubM(key, value); break; }
                    case "MulM": { result = MulM(key, value); break; }
                    case "DivM": { result = DivM(key, value); break; }
                    case "Exit": { Exit(); break; }

                    default: return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.MethodNotFound, "Method not found"));
                }
            }
            catch (Exception ex)
            {
                return Json(new JRPCError(body.Id, JSONVersion, JRPCErrorCodes.InternalError, $"Internal server error: {ex.Message}"));
            }

            //Exit();

            return Json(new ResJsonRPC()
            {
                Id = body.Id,
                Jsonrpc = jsonrpc,
                Method = body.Method,
                Result = result
            }, JsonRequestBehavior.AllowGet);
        }

        private int? SetM(string k, int x)
        {
            HttpContext.Session[k] = x;
            return GetM(k);
        }

        private int? GetM(string k)
        {
            object result = HttpContext.Session[k];
            return result == null ? null : (int?)int.Parse(result.ToString());
        }

        private int? AddM(string k, int x)
        {
            int? value = GetM(k);
            HttpContext.Session[k] = value == null ? x : value + x;
            return GetM(k);
        }

        private int? SubM(string k, int x)
        {
            int? value = GetM(k);
            HttpContext.Session[k] = value == null ? x : value - x;
            return GetM(k);
        }

        private int? MulM(string k, int x)
        {
            int? value = GetM(k);
            HttpContext.Session[k] = value == null ? x : value * x;
            return GetM(k);
        }

        private int? DivM(string k, int x)
        {
            int? value = GetM(k);
            HttpContext.Session[k] = value == null ? x : value / x;
            return GetM(k);
        }

        private void Exit()
        {
            HttpContext.Session.Clear();
            ignoreMethods = true;
        }
    }
}