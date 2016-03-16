using CalcProject;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalcProjectTest
{
    [TestClass]
    public class CalcTest
    {
        [TestMethod]
        public void TestAdd()
        {
            Assert.AreEqual(5, Calculator.plus(3, 2));
        }
        
    }
}
