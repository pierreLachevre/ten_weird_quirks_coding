using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace test
{
    class Inheritance
    {

        public class Base
        {
        }

        public interface Base2{

        }

        public class Derived : Base, Base2
        {

        }

         static void Main(string[] args)
        {            
           Derived derived=new Derived();
        }

    }
}