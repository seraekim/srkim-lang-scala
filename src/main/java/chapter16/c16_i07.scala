package chapter16

/**
 * 16.7 List 클래스의 고차 메소드
 *
 * 고차 연산자(higher-order operator)는 연산자 표기법으로 인자로 다른 함수를 받는 함수다.
 */
object c16_i07 extends App {
  /*
   * 리스트 매핑: map, flatMap, foreach
   * 
   * xs: List[T] map f: T => U
   * 모든 원소에 함수 f를 적용한 리스트를 반환
   */
  List(1, 2, 3) map (_ + 1)

  val words = List("the", "quick", "brown", "fox")
  words.map(_.length)
  words.map(_.toList.reverse.mkString)

  // flatMap은 오른쪽 피연산자로 원소의 리스트를 반환. 모든 리스트를 연결한 단일 리스트 반환
  println(words map (_.toList))
  println(words flatMap (_.toList))

  println(List.range(1, 5) flatMap { i => List.range(1, i) map (j => (i, j)) })
  println(List.range(1, 1))

  val r = for {
    i <- List.range(1, 5)
    j <- List.range(1, i)
  } yield (i, j)
  println(r)

  val intList = List(1, 2, 3, 4, 5)
  // foreach 는 오른쪽 피연산자에 Unit(프로시저)를 받음.
  var sum = 0
  intList foreach (sum += _)

  /*
   * 리스트 걸러내기: filter, partition, find, takeWhile, dropWhile, span
   * 
   * xs:List[T] filter p: T => Boolean
   * xs:List[T] partition p: T => Boolean, filter의 참거짓 순서쌍(튜플) 반환
   * xs:List[T] find p: T => Boolean, filter와 같으나 첫 원소만 반환
   * 
   * takeWhile 술어 p를 만족하는 가장 긴 접두사 반환
   * dropWhile 술어 p를 만족하는 가장 긴 접두사 제거
   * splitAt이 take, drop을 묶듯이, span은 takeWhile, dropWhile을 묶는다. 순서쌍 반환
   */
  intList filter (_ % 2 == 0)
  words filter (_.length == 3)

  intList partition (_ % 2 == 0)

  intList find (_ % 2 == 0) // Option[Int] = Some(2)
  intList find (_ <= 0) // Option[Int] = None

  println(List(1, 2, 3, -4, 5) takeWhile (_ > 0))
  println(List(1, 2, 3, -4, 5) dropWhile (_ > 0))
  println(words dropWhile (_ startsWith "t"))
  println(List(1, 2, 3, -4, 5) span (_ > 0))

  /*
   * 리스트 전체에 대한 술어: forall과 exists
   * 
   * xs forall p, 리스트의 모든 원소가 p를 만족할 때 true
   * xs exists p, 리스트의 원소 하나라도 p를 만족할 때 true
   * 
   * 예를 들어, 리스트의 리스트로 표현한 행렬에 모든 원소가 0인 행이 있는지 찾아보려면 다음과 같이 한다.
   */
  // 빈리스트도 0으로 판단해버리길래.. 한번 추가해보았다. 아무래도 List[Int] 니까 List() 는 List(0) 으로 판단하는건가 싶다.
  def hasZeroRow(m: List[List[Int]]) = m exists (row => row != List() && (row forall (_ == 0)))
  val row = List(List(0, 0, 0), List(0, 0, 1), List(0, 1, 0))
  val row2 = List(List(), List(0, 0, 1), List(0, 1, 0))
  println(hasZeroRow(row))
  println(hasZeroRow(row2))
  
  /*
   * 리스트 폴드: /:과 :\
   * 
   * 또 다른 일반적인 연산으로 리스트의 원소를 어떤 연산자를 가지고 결합하는 것이다.
   * 왼쪽폴드연산 /: => (z /: xs)(op)
   * z=시작값, xs=폴드할 대상 리스트, op=이항연산자
   * (z /: List(a, b, c)) (op) = op(op(op(z,a),b),c)
   * 오른쪽 폴드는 반대로..
   * (List(a, b, c) :\ z) (op) = op(a, op(b, op(c,z)))
   */
  def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)
  def product(xs: List[Int]): Int = (1 /: xs) (_ * _)
  println(sum(List(1,3,5))) // = 0 + 1 + 3 + 5
  println(product(List(1,3,5))) // = 1 * 1 * 3 * 5
  
  // 다음 경우에서 Right가 Left보다 효율적이라는데 정확한 이유는 ?
  def flattenLeft[T](xss: List[List[T]]) = (List[T]() /: xss)(_ ::: _)
  def flattenRight[T](xss: List[List[T]]) = (xss :\ List[T]())(_ ::: _)
}