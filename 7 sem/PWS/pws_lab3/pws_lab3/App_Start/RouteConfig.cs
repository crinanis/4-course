using System.Web.Http;
using System.Web.Mvc;
using System.Web.Routing;

namespace pws_lab3
{
    public class RouteConfig
    {
        public static void RegisterHttpRoutes(HttpConfiguration config)
        {
            config.Formatters.XmlFormatter.Indent = true;
            config.Formatters.JsonFormatter.Indent = true;


            config.Routes.MapHttpRoute(
                name: "HomeApi",
                routeTemplate: "api",
                defaults: new { controller = "Home" }
            );

            config.Routes.MapHttpRoute(
            name: "DefaultApiStudents",
            routeTemplate: "api/{controller}/{id}",
            defaults: new { id = RouteParameter.Optional }
            );

            //config.Routes.MapHttpRoute(
            //    name: "DefaultApiWithExtension",
            //    routeTemplate: "api/{controller}.{format}/{id}",
            //    defaults: new { id = RouteParameter.Optional, format = RouteParameter.Optional}
            //);

            config.Routes.MapHttpRoute(
                name: "ErrorsApi",
                routeTemplate: "api/errors/{code}",
                defaults: new { controller = "Errors", code = RouteParameter.Optional }
            );
        }
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}",
                defaults: new { controller = "Home", action = "Index" }
            );
        }
    }
}
