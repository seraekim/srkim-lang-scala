package chapter12

/**
 * 12.3 예제: 직사각형 객체
 */
object c12_i03 extends App {
  val rect = new Rectangle(new Point(1,1), new Point(10,10))
  println(rect.left)
  println(rect.right)
  println(rect.width)
}

class Point(val x: Int, val y: Int)

// 트레이트 없이 제작해보기..
/*class Rectangle(val topLeft: Point, val bottomRight: Point) {
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // 여러 기하 관련 메소드...
}*/
// 2차원 그래피  위젯..
/*abstract class Component {
  def topLeft: Point
  def bottomRight: Point
  
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // 여러 기하 관련 메소드...
}*/

/*
 * 풍부한 트레이트 정의
 */
trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}

abstract class Component extends Rectangular {
  
}

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  
}


