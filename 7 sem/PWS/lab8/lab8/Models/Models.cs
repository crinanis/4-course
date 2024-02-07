using System.Collections.Generic;

namespace lab8.Models
{
    public class ParamsData
    {
        public string Key { get; set; }
        public string Value { get; set; }
    }

    public class ReqJsonRPC
    {
        public string Id { get; set; }
        public string Jsonrpc { get; set; }
        public string Method { get; set; }
        public ParamsData Params { get; set; }
        public List<object> PositionalParams { get; set; }

    }

    public class ResJsonRPC
    {
        public string Id { get; set; }
        public string Jsonrpc { get; set; }
        public string Method { get; set; }
        public int? Result { get; set; }
    }

    public class JRPCError
    {
        public string Id { get; set; }
        public string Jsonrpc { get; set; }
        public ErrorInfo Error { get; set; }

        public JRPCError()
        {
            Id = "0";
            Jsonrpc = "2.0";
            Error = new ErrorInfo(400, "Error");
        }

        public JRPCError(string id, string jsonrpc, int code, string message)
        {
            Id = id;
            Jsonrpc = jsonrpc;
            Error = new ErrorInfo(code, message);
        }
    }

    public class ErrorInfo
    {
        public int Code { get; set; }
        public string Message { get; set; }
        public ErrorInfo(int code, string message)
        {
            Code = code;
            Message = message;
        }
    }

    public static class JRPCErrorCodes
    {
        public const int ParseError = -32700;
        public const int InvalidRequest = -32600;
        public const int MethodNotFound = -32601;
        public const int InvalidParams = -32602;
        public const int InternalError = -32603;
        public const int ServerErrorStart = -32000;
        public const int ServerErrorEnd = -32099;
    }
}