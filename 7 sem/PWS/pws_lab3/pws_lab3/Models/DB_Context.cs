using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace PWS_3.Models
{
    public class DB_Context : DbContext
    {
        public DB_Context() : base("ConnectionString")
        { }

        public DbSet<Student> Students { get; set; }

        public List<Student> GetList(int limit, string sort, int offset, int minid, int maxid, string like, string globallike)
        {
            var students = (IQueryable<Student>)this.Students;
            students = students.Where(s => s.Id >= minid).Where(s => s.Id <= maxid);
            if (globallike != null)
                students = students.Where(s => (s.Id.ToString() + s.Name + s.Phone.ToString()).Contains(globallike));
            if (like != null)
                students = students.Where(s => s.Name.Contains(like));
            if (sort == "asc")
                students = students.OrderBy(s => s.Name);
            else
                students = students.OrderByDescending(s => s.Name);
            students = students.Take(limit + offset).Skip(offset);
            return students.ToList();
        }

        public Student GetOne(int id)
        {
            var students = this.Students.ToList();
            int index = students.IndexOf(students.Find(x => x.Id == id));
            if (index != -1)
                return students[index];
            else
                return null;
        }

        public Student Post(string name, string phone)
        {
            var students = this.Students;
            int id = 0;
            foreach (Student stud in students)
            {
                if (stud.Id > id)
                {
                    id = stud.Id;
                }
            }
            Student student = new Student { Id = id + 1, Name = name, Phone = phone };
            this.Students.Add(student);
            this.SaveChanges();
            return student;
        }

        public Student Put(int id, string name, string phone)
        {
            var students = this.Students.ToList();
            int index = students.IndexOf(students.Find(x => x.Id == id));
            if (name != null)
                students[index].Name = name;
            if(phone != null)
                students[index].Phone = phone;
            this.SaveChanges();
            return students[index];
        }

        public Student Delete(int id)
        {
            var students = this.Students.ToList();
            Student removed = students.Find(x => x.Id == id);
            this.Students.Remove(removed);
            this.SaveChanges();
            return removed;
        }
    }
}