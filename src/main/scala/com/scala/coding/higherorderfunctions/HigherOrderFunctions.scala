package com.scala.coding.higherorderfunctions

class Decorator(left: String, right: String) {
  def layout[A](x: A) = left + x.toString() + right
}


object Test1 extends App {
  // apply function take 2 parameters a function f and value v, applies value v to function f
  def apply(f: Int => String, v: Int) = f(v)
  val decorator = new Decorator("[", "]")
  println(apply(decorator.layout, 7))
}

// In this test we will try to use higher order functions to compute different sums
object Test2 extends App {

  def id(x: Int) :Int = x

  // give me sum from a to b
  def rangeSum(a: Int, b: Int) : Int = {
    if (a > b) 0
    id(a) + rangeSum(a+1, b)
  }

  def square(x: Int): Int = x*x

  // give me sum of squares from a to b
  def squareSum(a: Int, b: Int): Int = {
    if (a > b) 0
    square(a) + squareSum(a + 1, b)
  }

  def cube(x: Int): Int = x*x*x

  // give me sume of cubes from a to b
  def cubeSum(a: Int, b: Int): Int = {
    if (a > b) 0
    cube(a) + cubeSum(a+1, b)
  }

  // consider adding more cases like above, we would need to write lot of functions, one for each case
  // see the pattern? if i can pass a function and use that function to replace rangeSum, squareSum and cubeSum then
  // i can basically write the same code in following way

  def sumUsingHighOrderFunction(f: Int => Int, x: Int, y: Int): Int = {
    if (x > y) 0
    f(x) + sumUsingHighOrderFunction(f, x+1, y)

  }

  // now i can pass different implementation of f and get desired result with less code

  def sumIntegers(a: Int, b: Int) = sumUsingHighOrderFunction(id, a, b)
  def sumSquares(a: Int, b: Int) = sumUsingHighOrderFunction(square, a, b)
  def sumCubes(a: Int, b: Int) = sumUsingHighOrderFunction(cube, a, b)


}

// In above example i can simply pass anonymous functions to achieve the same
object Test3 extends App {

  def sumUsingHighOrderFunction(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    f(a) + sumUsingHighOrderFunction(f, a+1, b)

  }

  // watch anonymous functions in action
  def sumIntegers(a: Int, b: Int) = sumUsingHighOrderFunction(x => x, a, b)
  def sumSquares(a: Int, b: Int) = sumUsingHighOrderFunction(x => x*x, a, b)
  def sumCubes(a: Int, b: Int) = sumUsingHighOrderFunction(x => x*x*x, a, b)


}
