package chapter19

/**
 * 19.4 변성 표기 검사
 * 
 * 필드 재할당 뿐만 아니라, 다음처럼, 메소드 파라미터의 타입으로까지 영향도가 있으면
 * 공변적일 수가 없다.
 * 
 * covariant type T occurs in contravariant position in type T of value x
 * Queue 트레이트에 +T 로 변경하면 위와 같은 컴파일 에러 문구가 뜨는데, 이는 재할당 가능한 필드는
 * +로 표시한 타입 파라미터를 메소드 파라미터에 사용할 수 없다는 규칙의 특별한 경우일 뿐이다.
 * 
 * 18.2절에서 스칼라는 재할당 가능한 필드 var x: T를 게터, 세터로 취급한다고 했다.
 * 
 * 변성 지정의 올바름을 검증하기 위해, 스칼라 컴파일러는 클래스나 트레이트 본문의 모든 위치를
 * 긍정적, 부정적, 중립적으로 구분한다. 위치는 타입 파라미터를 사용할 수 있는 모든 곳을 가리킨다.
 * 
 * 선언 중인 클래스의 최고 수준의 위치들은 긍정적으로 간주하며, 메소드 값 파라미터 위치를 식별 시엔
 * 메소드 바깐의 위치의 구분을 뒤집는다.
 * 
 * 각 위치의 변성 구분을 유지하기가 매우 어려운데, 컴파일러가 이런일을 대신해준다.
 */
/*
class StrangeIntQueue extends Queue[Int] {
  override def enqueue(x: Int) = {
    println(math.sqrt(x))
    super.enqueue(x)
  }
}
val x: Queue[Any] = new StrangeIntQueue
x.enqueue("Abc")
*/
object c19_i04 extends App {
  
}