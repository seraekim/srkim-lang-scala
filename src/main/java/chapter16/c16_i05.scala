package chapter16

/**
 * 16.5 리스트 패턴
 *
 * 패턴 매치를 사용해 각 부분으로 나눌 수 있다.
 *
 * 패턴에서 p op q 같은 중위 연산자는 op(p, q) 와 같다.
 * 즉 스칼라는 중위 연산자를 생성자 패턴으로 다룬다.
 * 특히 x :: xs 같은 콘즈 패턴은 ::(x, xs)로 바뀐다. 이는 이 패턴의 생성자에 해당하는
 * ::라는 클래스가 있어야만 함을 넌지시 알려준다. scala.::가 존재하며, 비어있지 않은 리스트를
 * 만드는 클래스다. scala 패키지 않에있는 ::, List 클래스의 메소드 :: 두 가지로 존재.
 *
 * 패턴으로 리스트를 분해하는 것은 기본 연산자인 head, tail, isEmpty를 호출해서 리스트의
 * 내부 필드를 얻는 것에 대한 대안이다.
 */
object c16_i05 extends App {
  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  val List(a, b, c) = fruit
  // 크기를 딱 알 수 없는 경우...
  val a2 :: b2 :: anyletter = fruit // anyletter: List[String]

  // 삽입 정렬의 패턴 매치 버전
  def isort(xs: List[Int]): List[Int] = xs match {
    case List()   => List()
    case x :: xsl => insert(x, isort(xsl))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => 
      if (x <= y) x :: xs
      else y :: insert(x, ys)
  }

  println(isort(List(5, 3, 4, 2, 7, 8, 2, 9, 1)))
}