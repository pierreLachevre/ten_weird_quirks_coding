using System;

namespace test
{
    class Comparison
    {
        static void Main(string[] args)
        {            
            string x = new string(new char[0]);
            string y = new string(new char[0]);
            Console.WriteLine(object.ReferenceEquals(x, y));
        }
    }
}

