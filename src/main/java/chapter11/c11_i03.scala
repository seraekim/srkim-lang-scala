package chapter11

/**
 * 11.3 바닥에 있는 타입
 * 
 * Null은 null 참조의 타입으로, 모든 참조타입(AnyRef가 슈퍼클래스)의
 * 서브클래스다. 값 타입과 호환성도 없다.
 * 
 * Nothing 타입은 스칼라 클래스 계층의 맨 밑바닥에 존재한다. 모든 다른 타입의 서브타입이다.
 * 
 * 7.4절에서 봤듯이, 비정상적 종료를 표시할 때 쓰인다.
 * 실제로 값을 반환하는 대신 예외를 던진다.
 * 
 */
object c11_i03 {
  // Predef 객체의 error 메소드
  def error(message: String): Nothing =
    throw new RuntimeException(message)
  
  /*
   * Notihng은 다양한 곳에서 유연하게 쓸 수 있다.
   * else 표현식에서는 Nothing을 반환하지만 실제로는 넘기는게 아니고
   * error 넘긴다. 따라서 표현식과 조화되면서 리턴 타입은 Int로 남는다.
   */
  def divide(x: Int, y: Int): Int =
    if (y != 0) x / y
    else error("can't divide by zero")
  divide(1,0)
}