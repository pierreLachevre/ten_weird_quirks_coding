using System;
using System.Drawing;

namespace test
{
    class CircleSquare
    {
        static void Main(string[] args)
        {
            Circle circle1 = new Circle(Color.Black);
            Circle circle2 = circle1;
            circle2.color = Color.Blue;
            Console.WriteLine(circle1.color);     
            Console.WriteLine(circle2.color);     
        }
    }
}
