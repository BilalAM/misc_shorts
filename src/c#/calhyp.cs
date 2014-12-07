using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication25
{
    class Program
    {
        static void Main(string[] args)

        {
            double a, b, c;
            Console.WriteLine("\n\t CALCULATING HYPOTENUSE BY USING THE PYTHAGORAS THEORM");
            Console.WriteLine("\t-------------------------------------------------------");
            Console.Write("\n\n* BASE MEASUREMENT(m) \t\t=> ");
            b = Convert.ToDouble(Console.ReadLine());
            Console.Write("\n* PERPENDICULAR MEASUREMENT(m) \t=> ");
            a = Convert.ToDouble(Console.ReadLine());

            c = Math.Sqrt((a * a) + (b * b));   //  Math.Sqrt used for taking square root
            
            Console.WriteLine("\n\n* THE HYPOTENUSE(m) \t\t=> {0} " , c);
            
            Console.ReadKey();
        }
  
    }
}
