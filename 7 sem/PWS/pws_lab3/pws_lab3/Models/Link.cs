namespace PWS_3.Models
{
    public class Link
    {
        public string Href { get; set; }
        public string Method { get; set; }
        public string Message { get; set; }

        public Link()
        {
            Href = null;
            Method = null;
            Message = null;
        }

        public Link(string href, string method, string message)
        {
            Href = href;
            Method = method;
            Message = message;
        }
    }
}