using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;

namespace lab2.Models
{
    public class Data
    {
        public static int result = 0;
        public static Stack<int> stack = new Stack<int>();

        public static string Get()
        {
            int _result = result;
            if (stack != null)
            {
                if (stack.Count != 0)
                {
                    _result += stack.Peek();
                }
            }

            string json = JsonConvert.SerializeObject(new { RESULT = _result });
            return json;
        }

        public static void Post(int _result)
        {
            result = _result;
        }

        public static void Put(int add)
        {
            stack.Push(add);
        }

        public static void Delete()
        {
            if (stack.Count() != 0)
            {
                stack.Pop();
            }
        }

        public static int StackState()
        {
            return stack.Count();
        }
    }
}