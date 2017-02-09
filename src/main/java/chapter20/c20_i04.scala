package chapter20

/**
 * 20.4 추상 var
 * 
 * 추상 val과 마찬가지로 이름과 타입만을 사용한다.
 * 
 * 18.2절에서 클래스의 멤버인 var에는 게터와 세터 메소드를 스칼라가 만들어준다는 사실을 보았다.
 * AbstractTime은 AbstractTimeReal과 같다.
 */
trait AbstractTime {
  var hour: Int
  var minute: Int
}
trait AbstracTimeReal {
  def hour: Int
  def hour_=(x: Int)
  def minute: Int
  def minute_=(x: Int)
}
object c20_i04 {
  
}