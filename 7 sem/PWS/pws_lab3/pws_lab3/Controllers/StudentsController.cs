using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using PWS_3.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web;
using System.Web.Http;
using System.Xml;
using System.Xml.Linq;

namespace PWS_3.Controllers
{
    public class StudentsController : ApiController
    {
        DB_Context context = new DB_Context();
       
        [HttpGet]
        [Route("api/Students.{format?}")]
        public object Get(HttpRequestMessage request, string format = "json")
        {
            Uri uri = request.RequestUri;
            //string format = request.Content.Headers.ContentType?.MediaType;
            try
            {
                var query = HttpUtility.ParseQueryString(uri.Query);

                string sort = query["sort"];
                int ParseInt(string key, int defaultValue = 0) => Int32.TryParse(query[key], out var value) ? value : defaultValue;

                int limit = ParseInt("limit", 999999);
                int offset = ParseInt("offset");
                int minid = ParseInt("minid");
                int maxid = ParseInt("maxid", 1000);

                string columns = query["columns"];
                string globallike = query["globallike"];
                string like = query["like"];

                List<Student> students = context.GetList(limit, sort, offset, minid, maxid, like, globallike);


                bool isId = false, isName = false, isNumber = false;
                if (columns != "" && columns != null)
                {
                    if (columns.Contains("id"))
                        isId = true;
                    if (columns.Contains("name"))
                        isName = true;
                    if (columns.Contains("number"))
                        isNumber = true;
                }
                else
                {
                    isId = true;
                    isName = true;
                    isNumber = true;
                }

                List<StudentDto> result = new List<StudentDto>();
                foreach (Student student in students)
                {
                    StudentDto dto = new StudentDto(student);
                    dto.Links = new Link[]
                        {
                            new Link("/api/students/" + student.Id, "GET", "Получить информацию"),
                            new Link("/api/students/" + student.Id, "PUT", "Обновить информацию"),
                            new Link("/api/students/" + student.Id, "DELETE", "Удалить студента")
                        };
                    dto.IdSpecified = isId;
                    dto.NameSpecified = isName;
                    dto.NumberSpecified = isNumber;
                    result.Add(dto);
                }
                var Links = new Link[]
                        {
                            new Link("/api/students/", "POST", "Добавить информацию"),
                        };
                Models.Array array = new Models.Array(result, Links);

                XmlDocument xmlDoc = new XmlDocument();
                XmlElement rootElement = xmlDoc.CreateElement("ArrayOfStudentDto");
                xmlDoc.AppendChild(rootElement);

                foreach (StudentDto dto in result)
                {
                    XmlElement studentElement = xmlDoc.CreateElement("StudentDto");

                    // Добавить данные для каждой записи
                    if (isId) AddXmlElement(xmlDoc, studentElement, "Id", dto.Id.ToString());
                    if (isName) AddXmlElement(xmlDoc, studentElement, "Name", dto.Name);
                    if (isNumber) AddXmlElement(xmlDoc, studentElement, "Number", dto.Number);

                    // Добавить Links
                    XmlElement linksElement = xmlDoc.CreateElement("Links");
                    foreach (var link in dto.Links)
                    {
                        XmlElement linkElement = xmlDoc.CreateElement("Link");

                        AddXmlElement(xmlDoc, linkElement, "Href", link.Href);
                        AddXmlElement(xmlDoc, linkElement, "Method", link.Method);
                        AddXmlElement(xmlDoc, linkElement, "Message", link.Message);

                        linksElement.AppendChild(linkElement);
                    }

                    studentElement.AppendChild(linksElement);
                    rootElement.AppendChild(studentElement);
                }

                if (format == "xml")
                {
                    XmlWriterSettings settings = new XmlWriterSettings
                    {
                        Indent = true,        // Включить форматирование
                        IndentChars = "    ", // Установить символы отступа
                        NewLineChars = "\n",    // Установить символ новой строки
                        NewLineOnAttributes = false, // Новая строка только после тегов, а не после атрибутов
                        NewLineHandling = NewLineHandling.Replace // Замена новых строк
                    };

                    using (StringWriter stringWriter = new StringWriter())
                    {
                        using (XmlWriter xmlWriter = XmlWriter.Create(stringWriter, settings))
                        {
                            xmlDoc.Save(xmlWriter);
                        }

                        HttpResponseMessage response = new HttpResponseMessage(HttpStatusCode.OK);
                        response.Content = new StringContent(stringWriter.ToString(), Encoding.UTF8, "application/xml");
                        return response;
                    }
                }
                else if (format == "json" || format == null) return Json(array);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
            catch (Exception e)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
        }

        [HttpGet]
        [Route("api/Students.{format?}/{id}")]
        public object Get(int id, HttpRequestMessage request, string format = "json")
        {
            Uri uri = request.RequestUri;
            
            //string format = request.Content.Headers.ContentType?.MediaType;

            try
            {
                string columns = HttpUtility.ParseQueryString(uri.Query).Get("columns");
                bool isId = false, isName = false, isNumber = false;
                if (columns != "" && columns != null)
                {
                    isId = columns.Contains("id");
                    isName = columns.Contains("name");
                    isNumber = columns.Contains("number");
                }
                else
                {
                    isId = true;
                    isName = true;
                    isNumber = true;
                }

                Student student = context.GetOne(id);
                StudentDto dto = new StudentDto(student);

                dto.IdSpecified = isId;
                dto.NameSpecified = isName;
                dto.NumberSpecified = isNumber;

                Link[] links = new Link[]
                {
                    new Link("/api/students/" + id, "PUT", "Update"),
                    new Link("/api/students/" + id, "DELETE", "Delete")
                };
                dto.Links = links;

                XmlDocument xmlDoc = new XmlDocument();
                XmlElement rootElement = xmlDoc.CreateElement("StudentDto");
                xmlDoc.AppendChild(rootElement);

                if (isId) AddXmlElement(xmlDoc, rootElement, "Id", (dto.Id).ToString());
                if (isName) AddXmlElement(xmlDoc, rootElement, "Name", dto.Name);
                if (isNumber) AddXmlElement(xmlDoc, rootElement, "Number", dto.Number);

                XmlElement linksElement = xmlDoc.CreateElement("Links");
                foreach (var link in dto.Links)
                {
                    XmlElement linkElement = xmlDoc.CreateElement("Link");

                    AddXmlElement(xmlDoc, linkElement, "Href", link.Href);
                    AddXmlElement(xmlDoc, linkElement, "Method", link.Method);
                    AddXmlElement(xmlDoc, linkElement, "Message", link.Message);

                    linksElement.AppendChild(linkElement);
                }

                rootElement.AppendChild(linksElement);

                if (format == "xml")
                {
                    XmlWriterSettings settings = new XmlWriterSettings
                    {
                        Indent = true,        // Включить форматирование
                        IndentChars = "    ", // Установить символы отступа
                        NewLineChars = "\n",    // Установить символ новой строки
                        NewLineOnAttributes = false, // Новая строка только после тегов, а не после атрибутов
                        NewLineHandling = NewLineHandling.Replace // Замена новых строк
                    };

                    using (StringWriter stringWriter = new StringWriter())
                    {
                        using (XmlWriter xmlWriter = XmlWriter.Create(stringWriter, settings))
                        {
                            xmlDoc.Save(xmlWriter);
                        }

                        HttpResponseMessage response = new HttpResponseMessage(HttpStatusCode.OK);
                        response.Content = new StringContent(stringWriter.ToString(), Encoding.UTF8, "application/xml");
                        return response;
                    }
                }
                else if (format == "json" || format == null) return Json(dto);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
            catch (Exception e)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
        }

        private void AddXmlElement(XmlDocument xmlDoc, XmlElement parentElement, string elementName, string value)
        {
            XmlElement element = xmlDoc.CreateElement(elementName);
            element.InnerText = value;
            parentElement.AppendChild(element);

        }

        [HttpPost]
        [Route("api/Students.{format?}")]
        public object Post(HttpRequestMessage request, string format = "json")
        {
            Uri uri = request.RequestUri;
            //string format = request.Content.Headers.ContentType?.MediaType;
            var body = request.Content;
            string json = body.ReadAsStringAsync().Result;
            dynamic data = JObject.Parse(json);
            if (data.name == null || data.number == null)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(400), Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(400), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }

            try
            {
                string nam = Convert.ToString(data.name);
                string num = Convert.ToString(data.number);
                Student student = context.Post(nam, num);
                StudentDto dto = new StudentDto(student);

                Link[] links = new Link[]
                {
                    new Link("/api/students/" + student.Id, "GET", "Получить студента"),
                };
                dto.Links = links;

                if (format == "xml") return Content(HttpStatusCode.OK, dto, Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Json(dto);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);

            }
            catch (Exception e)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
        }

        [HttpPut]
        [Route("api/Students.{format?}/{id}")]
        public object Put(int id, HttpRequestMessage request, string format = "json")
        {
            Uri uri = request.RequestUri;
            //string format = request.Content.Headers.ContentType?.MediaType;
            var body = request.Content;
            string json = body.ReadAsStringAsync().Result;
            dynamic data = JObject.Parse(json);
            string columns = HttpUtility.ParseQueryString(uri.Query).Get("columns");
            bool isId = false, isName = false, isNumber = false;
            if (columns != "" && columns != null)
            {
                isId = columns.Contains("id");
                isName = columns.Contains("name");
                isNumber = columns.Contains("number");
            }
            else
            {
                isId = true;
                isName = true;
                isNumber = true;
            }
            try
            {
                Student student = context.Put(id, Convert.ToString(data.name), Convert.ToString(data.number));
                StudentDto dto = new StudentDto(student);
                Link[] links = new Link[]
                {
                    new Link("/api/students/" + id, "GET", "Получить студента"),
                    new Link("/api/students/" + id, "DELETE", "Удалить студента")
                };
                dto.Links = links;

                XmlDocument xmlDoc = new XmlDocument();
                XmlElement rootElement = xmlDoc.CreateElement("StudentDto");
                xmlDoc.AppendChild(rootElement);

                if (isId) AddXmlElement(xmlDoc, rootElement, "Id", (dto.Id).ToString());
                if (isName) AddXmlElement(xmlDoc, rootElement, "Name", dto.Name);
                if (isNumber) AddXmlElement(xmlDoc, rootElement, "Number", dto.Number);

                XmlElement linksElement = xmlDoc.CreateElement("Links");
                foreach (var link in dto.Links)
                {
                    XmlElement linkElement = xmlDoc.CreateElement("Link");

                    AddXmlElement(xmlDoc, linkElement, "Href", link.Href);
                    AddXmlElement(xmlDoc, linkElement, "Method", link.Method);
                    AddXmlElement(xmlDoc, linkElement, "Message", link.Message);

                    linksElement.AppendChild(linkElement);
                }

                rootElement.AppendChild(linksElement);

                if (format == "xml")
                {
                    XmlWriterSettings settings = new XmlWriterSettings
                    {
                        Indent = true,        // Включить форматирование
                        IndentChars = "    ", // Установить символы отступа
                        NewLineChars = "\n",    // Установить символ новой строки
                        NewLineOnAttributes = false, // Новая строка только после тегов, а не после атрибутов
                        NewLineHandling = NewLineHandling.Replace // Замена новых строк
                    };

                    using (StringWriter stringWriter = new StringWriter())
                    {
                        using (XmlWriter xmlWriter = XmlWriter.Create(stringWriter, settings))
                        {
                            xmlDoc.Save(xmlWriter);
                        }

                        HttpResponseMessage response = new HttpResponseMessage(HttpStatusCode.OK);
                        response.Content = new StringContent(stringWriter.ToString(), Encoding.UTF8, "application/xml");
                        return response;
                    }
                }
                else if (format == "json" || format == null) return Json(dto);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);

            }
            catch (Exception e)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);

            }

        }

        [HttpDelete]
        [Route("api/Students.{format?}/{id}")]
        public object Delete(int id, HttpRequestMessage request, string format = "json")
        {
            Uri uri = request.RequestUri;
            //string format = request.Content.Headers.ContentType?.MediaType;
            var body = request.Content;
            string json = body.ReadAsStringAsync().Result;
            dynamic data = JObject.Parse(json);
            try
            {
                Student student = context.Delete(id);
                StudentDto dto = new StudentDto(student);

                if (format == "xml") return Content(HttpStatusCode.OK, dto, Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Json(dto);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
            catch (Exception e)
            {
                if (format == "xml") return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.XmlFormatter);
                else if (format == "json" || format == null) return Content(HttpStatusCode.BadRequest, new ErrorDto(500), Configuration.Formatters.JsonFormatter);
                else return Content(HttpStatusCode.BadRequest, new ErrorDto(501), Configuration.Formatters.JsonFormatter);
            }
        }

        [HttpGet]
        [Route("api/ResourceInfo")]

        public string GetResourceInfo()
        {
            var resourceInfo = new
            {
                Methods = new[] { "GET", "POST" },
                UriFormat = "/api/students.{xml/json}",
                Method = new[] {"PUT", "DELETE"},
                Uri = "/api/students.{xml/json}/{studentNum}"
            };

            var jsonInfo = JsonConvert.SerializeObject(resourceInfo);

            return jsonInfo;
        }
    }
}