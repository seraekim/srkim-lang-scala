package chapter21

/**
 * 21.3 예상 타입으로의 암시적 변환
 * 
 * 예상 타입으로 암시적 변환을 하는 것이 컴파일러가 암시를 사용하는 첫 번째 부분이다.
 * 규칙은 간단하다. 컴파일러가 Y 타입이 필요한 위치에 X 타입을 봤다면, X를 Y로 변환하는
 * 암시적 함수를 찾는다. 예를 들어 배정도 실수는 보통 정수로 쓰일 수 없다. 정수 쪽이 정밀도가
 * 떨어지기 때문이다.
 * 
 * 하지만 암시적 변환을 정의하면 이를 부드럽게 넘길 수 있다.
 * 
 * Double -> Int 암시적 변환은, 보이지 않는 곳에서 정밀도를 잃어버리는 것을 볼 때, 권장되는 변환이 아니다.
 * 
 * 스칼라에서는 Int -> Double 암시적 변환이 이미 있다.
 * scala.Predef 안에서 다음 변환을 찾아볼 수 있다.
 * implicit def int2double(x: Int): Double = x.toDouble
 * 
 * 스칼라 컴파일러는 위 변환을 특별히 다루어 i2d 바이트코드로 번역하여 자바와 같아진다.
 */
object c21_i03 extends App {
  // val i: Int = 3.5 // type mismatch; found : Double(3.5) required: Int
  /*
   * 스코프에 들어있는 단일식별자 암시적 변환 발견. 그렇지 않다면, import를 사용하거나, 상속을 통해 스코프 내로
   * 변환 함수를 가져와야 한다.
   */
  implicit def doubleToInt(x: Double) = x.toInt
  val i: Int = 3.5 // val i: Int = doubleToInt(3.5)와 같다.
}