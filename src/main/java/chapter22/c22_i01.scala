package chapter22

/**
 * 22장 리스트 구현
 * 
 * List 클래스는 스칼라에서 가장 널리 사용되는 구조화된 데이터 타입이다.
 * 16장에서 사용법을 봤다면, 이번 장에서는 덮개를 벗겨서 스칼라에서 리스트를 어떻게 구현하는지
 * 살펴볼 것이다.
 * 
 * List 클래스의 내부를 알아두면 여러 가지 이유에서 유용하다. 내부를 알면 리스트 연산의
 * 상대적인 효율성을 더 잘 이해할 수 있다. 따라서 리스트를 사용해 더 빠르고 간결한 코드를
 * 작성할 수 있다. 자신만의 라이브러리를 설계할 때 필요한 기법도 여럿 배울 수 있다.
 * 
 * List 클래스는 일반적인 스칼라 타입 시스템의 복잡한 응용의 예라 할 수 있고, 특히
 * 제네릭한 성질에 대한 좋은 예다.
 * 
 * 22.1 List 클래스 개괄
 * 
 * 두 개의 서브 클래스가 있는데 :: 와 Nil이다.
 * 
 * package scala
 * abstract class List[+T] { ...
 * 
 * List는 추상 클래스다. 따라서 빈 List 생성자를 호출해 리스트의 원소를 정의할 수가 없다.
 * 즉 new List 같은 표현식은 합법적이지 않다.
 * 
 * +T 는 List가 공변적이라는 뜻이므로 List[Int] 타입의 값을 List[Any] 같은 타입의 변수에
 * 할당할 수 있다.
 * 
 * 모든 리스트 연산은 세 가지 기본 메소드로 만들 수 있다.
 * 
 * def isEmpty: Boolean
 * def head: T
 * def tail: List[T]
 * 
 * 1. Nil 객체
 * 
 * Nil 객체는 빈 리스트를 정의한다.
 * 
 * case object Nil extends List[Nothing] ...
 * 
 * 2. :: 클래스
 * 
 * :: 는 콘즈(cons)라고 부르며 construct의 약자다. 이 클래스는 비어있지 않은 리스트를 표현한다.
 * 
 * final case class ::[T](head: T, tail: List[T]) extends List[T] { ...
 * 
 * 모든 케이스 클래스의 파라미터는 암시적으로 해당 클래스의 필드다.
 * 
 * 3. 추가 메소드
 * 
 * 다른 모든 리스트 메소드는 기본적인 세 메소드를 바탕으로 정의할 수 있다. (head, tail, isEmpty)
 * 
 * def length: Int = if (isEmpty) 0 else 1 + tail.length
 * def drop(n: Int): List[T] =
 *   if (isEmpty) Nil
 *   else if (n <= 0) this
 *   else tail.drop(n - 1)
 * 
 * def map[U](f : T => U): List[U] =
 *   if (isEmpty) Nil
 *   else f(head) :: tail.map(f)
 * 
 * 4. 리스트 구성
 * 
 * ::, :::는 특별하다. 콜론으로 끝나기에 오른쪽 피연산자에 바인딩 된다. x :: xs는
 * xs.::(x)
 * 
 * 콘즈 메소드가 받는 원소가 같은 원소만 받아야 된다고 정의한다면, val fruits 의 타입이 Fruit로 모아질 수가 없다.
 * 
 * def ::[U >: T](x: U): List[U] = new scala.::(x, this)
 * 추가하는 타입은 T의 슈퍼타입인 U이며 결과 값도 List[U]이다. U >: T 여야만 하는 이유는 List[+T] 로
 * List는 공변적이기 때문이다. 메소드의 파라미터는 반공변적인 위치이므로 하위바운드 타입[U >: T]는 필수다.
 * 
 * def :::[U >: T](prefix: List[U]): List[U] =
 *   if (prefix.isEmpty) this
 *   else prefix.head :: prefix.tail ::: this
 * 
 * right associative 오른쪽 결합이므로,
 * 위 식은 결국 다음과 같다. this.:::(prefix.tail).::(prefix.head)
 * ::: 메소드를 계속 재귀적으로 연산하면 결국 this.:::(Nil).::(prefix.eN) ~ ... ~ .::(prefix.e1).::(prefix.e0) 로
 * 볼 수 있고, 이것이 scala.::(e0, e1) 으로 하나하나 연산 되어 결국 원래 순서의 List가 출력된다.
 */
abstract class Fruit
class Apple extends Fruit
class Orange extends Fruit
object c22_i01 extends App {
  val xs = List(1, 2, 3)
  var ys: List[Any] = xs
  
  var n = xs.::(10)
  var n2 = n.:::(List(11,12))
  println(n2)
  
  val apples = new Apple :: Nil
  val fruits = new Orange :: apples
}