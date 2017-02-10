package chapter20

/**
 * 20.9 열거형
 * 
 * 경로 의존적 타입의 재미있는 활용 예 하나를 스칼라의 열거형 지원에서 찾아볼 수 있다.
 * 
 * 새 열거형을 만들고 싶다면, 아래 예와 같이 Enumeration 클래스를 확장한 객체를 만들면 된다.
 * 
 * Enumeration은 내부 클래스로 Value를 정의한다. 즉 Color.Red 등의 값은 타입이
 * Color.Value라는 뜻이다. 이 타입은 경로 의존 타입이다. Color 는 경로이고,
 * Value는 그 경로에 의존하는 타입이다.
 * 
 * Direction.Value는 Color.Value와는 다르다. 두 타입의 경로 부분이 다르기 때문이다.
 */
object Color extends Enumeration {
  val Red, Green, Blue = Value
}
/*
 * Value 메소드를 오버로드한 변형 메소드를 호출하면 열거형 값과 이름을 연관시킬 수 있다.
 */
object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

object c20_i09 extends App {
  // 이터레이션
  for (d <- Direction.values) print(d + " ")
  // 열거형 값은 번호가 붇는데 id 메소드를 사용해 찾을 수 있다.
  println(Direction.East.id)
  // 반대 방향으로도 작업이 가능하다. id로 부터 열거형 값으로 변환한다.
  println(Direction(1))
}