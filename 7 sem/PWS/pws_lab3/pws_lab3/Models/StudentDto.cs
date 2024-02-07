using Newtonsoft.Json;
using System.Xml.Serialization;

namespace PWS_3.Models
{
    [XmlRoot("Student")]
    public class StudentDto
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Number { get; set; }
        [XmlIgnore]
        [JsonIgnore]
        public bool IdSpecified { get; set; } = true;

        [XmlIgnore]
        [JsonIgnore]
        public bool NameSpecified { get; set; } = true;

        [XmlIgnore]
        [JsonIgnore]
        public bool NumberSpecified { get; set; } = true;

        [XmlArray("Links")]
        [XmlArrayItem("Link")] 
        public Link[] Links { get; set; }

        public StudentDto(Student student)
        {
            this.Id = student.Id;
            this.Number = student.Phone;
            this.Name = student.Name;
        }
        public StudentDto()
        {
            this.Id = 0;
            this.Number = null;
            this.Name = null;
        }
    }
}