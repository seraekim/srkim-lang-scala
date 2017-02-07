package chapter19

/**
 * 19.3 변성 표기
 * 
 * 19.2에서 정의한 Queue는 트레이트이며 타입이 아니다. Queue가 타입이 아닌 이유는 타입 파라미터를 받지 않기 때문이다.
 * def doesNotCompile(q: Queue) {}
 * 
 * 그 대신 Queue 트레이트는 파라미터화된 타입을 지정하도록 허용한다.
 * def doesCompile(q: Queue[AnyRef]) {}
 * 
 * 따라서, Queue는 트레이트 또는 타입 생성자(type constructor)이며,
 * Queue[String]은 타입이다.
 * 
 * 또한 Queue를 제네릭 트레이트라 부를 수도 있다. 제네릭 큐라고 하자면, Queue[Int]는 구체적 큐다.
 * 
 * S가 T의 서브타입일 때 Queue[S]가 Queue[T]의 서브타입이라면, 타입 파라미터 T에 대해 공변적(covariant, flexible)
 * 이다. 타입은 한자리 밖에 없으니 그냥 공변적이라고 표현해도 좋다. 그러나 스칼라에서 제네릭 타입은 기본적으로
 * 무공변(nonvariant)이다. 따라서 Queue[String]을 Queue[AnyRef] 대신 사용할 수 없다.
 * 
 * trait Queue[+T] { ... } 큐의 서브타입 관계에 공변성(유연성)을 요구할 수 있다.
 * 
 * trait Queue[-T] { ... } 반공변(contravariant) 서브타입 관계를 알려준다. 이는 T가 S의 서브타입인 경우
 * Queue[S]가 Queue[T]의 서브타입이라는 뜻이다. 공변, 반공변, 무공변 여부를 파라미터의 변성(variance)이라 부른다.
 * +, - 기호는 변성 표기(variane annotation)라고 부른다.
 * 
 * 순수 함수형 세계에서, 여러 타입은 태생적으로 공변적(유연함)이다. 하지만 변경 가능 데이터를 도입하면 상황이 바뀐다.
 */
// 무공변성(융통성이 없는) Cell 클래스
class Cell[T](init: T) {
  private[this] var current = init
  def get = current
  // covariant type T occurs in contravariant position in type T of value x
  def set(x: T) { current = x }
}
/*
 * 위 클래스는 Cell[+T]가 컴파일러를 통과할리가 없지만 통과되었다 가정한다면
  val c1 = new Cell[String]("abc")
  val c2: Cell[Any] = c1
  c2.set(1)
  val s: String = c1.get
  
    위 코드는 실행이 과연 잘 될까? 개별적으로 보자면 올바른 듯 하지만.. 전체를 이어주게 되면
  String에 Int를 할당하는 것이다. 이는 물론 타입 건전성에 위배된다.
 * 이런 실행 연산 시점 오류에 대해 어느 연산을 비난해야 할까?
 * 공변성 서브타이핑을 하는 두번째 부분이다. String의 Cell은 Any의 Cell일 수가 없다.
 */

/*
 * 1. 변성과 배열
 * 
 * 이 동작을 자바 배열과 비교해보면 재미있다. 자바는 배열을 공변적으로 다룬다.
 * 
 * // 자바 코드
 * String[] a1 = { "abc" };
 * Object[] a2 = a1;
 * a2[0] = new Integer(17);
 * String s = a1[0];
 * 
 * 위 예제는 컴파일까지는 잘 됨을 알 수 있다. 실행에서는 a2[0]에 Integer를 할당한다는
 * ArrayStore 예외가 발생한다.
 * 
 * 자바는 실행 시점에 원소의 타입을 저장한다. 그리고 원소를 변경할 때마다, 새 원소 값을 배열에 저장한 원소 타입과 비교한다.
 * 자바가 왜 이런 설계를 채택했을까 의문이다. 왜냐면 안전하지도 않고, 비용도 비싸기 때문이다. 자바의 설계자인 고슬링 James Gosling은
 * 그런 질문을 받자 배열을 제네릭하게 다룰 간단한 방법이 필요했기 때문이라 답했다.
 * 
 * 다음과 같이 Object의 배열을 받는 시그니처의 메소드를 가지고 모든 배열을 정렬할 수 있기를 원했던 것이다.
 * 그러니까 쉽게 말하자면, 자바의 다형성으로 통일된 메소드 (Object 파라미터)를 가질 수 있는 것이다. (다형성의 이점이다)
 * void sort(Object[] a, Comparator cmp) { ... }
 * 
 */
object c19_i03 extends App {
  /* 
   * 스칼라는 배열을 공변적으로 다루지 않는다는 점에서 자바보다 더 순수하다.
   * 스칼라의 Array는 무공변성으로 다뤄진다. 따라서 자바 배열을 흉내내려면 자바의 슈퍼타입 Object로 모아주면 가능하지만
   * 마찬가지로 실행시점에서 ArrayStore 예외가 발생할 수 있다.
   */
  val a1 = Array("abc")
  val a2: Array[Object] = a1.asInstanceOf[Array[Object]]
  
}