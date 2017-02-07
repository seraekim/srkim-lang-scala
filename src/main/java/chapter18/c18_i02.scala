package chapter18

/**
 * 18.2 재할당 가능한 변수와 속성
 * 
 *  - 값을 읽기
 *  - 새로운 값을 할당
 *  
 *  자바빈즈에서는 getter, setter 메소드로 캡슐화한다.
 *  
 *  스칼라의 어떤 객체의 멤버 중 비공개가 아닌 모든 var 멤버에 게터와 세터를 자동으로 정의.
 *  var x의 게터는 x, 세터는 x_=이다.
 *  
 *  var hour = 12
 *  재할당 가능한 필드에 대해 게터 hour, 세터 hour_=가 생김. 이 경우 필드에는 항상
 *  private[this]가 붙는다. var가 공개 멤버라면, 게터 세터도 공개이고 보호멤버라면
 *  게터 세터 또한 보호 멤버다.
 *  
 *  어떤 변수에 대한 모든 접근을 로그로 남기기 위해 게터 세터 활용할 수 있으며, 변수에 이벤트를 접목해서
 *  어떤 변수를 변경할 때마다 구독(subscribe)을 요청한 다른 객체들에게 통지하게 만들 수도 있다.(35장)
 */
class Time {
  var hour = 12
  var minute = 0
}
class Time2 {
  private[this] var h = 12
  private[this] var m = 0
  
  def hour: Int = h
  def hour_=(x: Int) {
    require(0 <= x && x < 24)
    h=x
  }
  
  def minute: Int = m
  def minute_=(x: Int) {
    require(0 <= x && x < 60)
    m=x
  }
}
// fahrenheit 필드는 정의하지 않았지만, 연관된 getter / setter 정의가 가능하다.
class Thermometer {
  // 변수 초기화 = _ 디폴트 값 넘김
  var celsius: Float = _
  // 초기화 하지 않고, 추상 변수를 선언해버림(20장)
  // var celsius2: Float
  def fahrenheit = celsius * 9 / 5 + 32
  def fahrenheit_= (f:Float) {
    celsius = (f - 32) * 5 / 9
  }
  override def toString = fahrenheit + "F/"+ celsius + "C"
}
object c18_i02 extends App {
  val t = new Thermometer
  println(t)
  t.celsius = 100 // celsius_= 호출
  println(t)
  t.fahrenheit = -40 // fahrenheit_= 호출
  println(t)
}