package chapter23

/**
 * 23장 for 표현식 다시 보기
 * 
 * 16장에서는 map, flatMap, filter 같은 고차 함수가 리스트를 처리하는 강력한
 * 구성요소가 될 수 있음을 살펴봤다. 하지만 때로 이런 함수를 사용해 너무 추상화를 많이 한 프로그램은
 * 이해하기 어려울 수 있다.
 * 
 */
case class Person(name: String, isMale: Boolean, children: Person*)
object c23_i01 extends App {
  val lara = Person("Lara", false)
  val bob = Person("Bob", true)
  val julie = Person("Julie", false, lara, bob)
  val persons = List(lara, bob, julie)
  
  // 리스트에서 모든 엄마와 자식의 쌍을 찾아내고 싶다면...
  val mumWithChildren = persons filter (p => !p.isMale) flatMap (p => (p.children map ( c => (p.name, c.name))))
  println(mumWithChildren) // List((Julie,Lara), (Julie,Bob))
  
  // 이 예제를 filter 대신 withFilter를 사용해 최적화할 수 있다. 여자를 나타내는 중간 데이터 구조를 만들 필요가 없어진다.
  persons withFilter (p => !p.isMale) flatMap (p => (p.children map ( c => (p.name, c.name))))
  
  // 위 질의문은 잘 작동하지만.. 이해하거나 작성하기가 쉽진 않다.. 7.3절에서 본 for 표현식을 써보자.
  val mumWithChildren2 = for (p <- persons; if !p.isMale; c <- p.children) yield (p.name, c.name)
  /*
   * 스칼라는 yield 하는 모든 for 표현식을  map, flatMap, withFilter 등의 고차 메소드 호출을 사용해 변환한다.
   * yield가 없는 for 표현식은 withFilter와 foreach만을 사용해 변환한다.
   */
  
  /*
   * 23.1 for 표현식
   * 
   * 일반적으로 for 표현식은 다음과 같은 형태다
   * for (seq) yield expr
   * 
   * 여기서 seq는 generator, 정의, 필터를 나열한 것이다. 연속적인 구성요소 사이에는 세미콜론을 넣는다.
   * 정의의 경우 val을 써도 되고 생략해도 된다.
   */
  // 아래 예는 제너레이터; 정의; 필터 순으로 쓰였다. 
  for(p <- persons; n = p.name; if (n startsWith "To")) yield n
  // 아래처럼 쓸 수도 있다. (7.3절 참고)
  for {
    p <- persons
    n = p.name
    if (n startsWith "To")
  } yield n
  /*
   *  모든 for 표현식은 제너레이터에서 시작한다. 둘이상 있다면, 앞의 것이 바깥 for, 뒤의 것이 안쪽 for문의 역할을 한다.
   *  다음과 같이 테스트해보자.
   */
  for (x <- List(1,2); y <- List("one", "two")) yield(x,y)
}