using System;
using System.Drawing;

namespace test
{
    class CircleSquare
    {
        static void Main(string[] args)
        {
            testSquare();
            testCircle();    
        }

        static void testSquare(){
            Square square1 = new Square(20, 30);
            Square square2 = square1;
            square2.X = 50;
            Console.WriteLine(square1.X);     
            Console.WriteLine(square2.X); 
        }

        static void testCircle(){
            Circle circle1 = new Circle(Color.Black);
            Circle circle2 = circle1;
            circle2.color = Color.Blue;
            Console.WriteLine(circle1.color);     
            Console.WriteLine(circle2.color); 
        }
    }
}
