using System.Collections.Generic;

namespace PWS_3.Models
{
    public class Array
    {
        public List<StudentDto> StudentDtos { get; set; }
        public Link[] Link { get; set; }

        public Array()
        {
            StudentDtos = null;
            Link = null;
        }

        public Array(List<StudentDto> studentDtos, Link[] link)
        {
            StudentDtos = studentDtos;
            Link = link;
        }
    }
}