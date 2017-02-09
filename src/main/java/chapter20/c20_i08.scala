package chapter20

import java.io.PrintWriter
import java.util.Date

/**
 * 20.8 구조적 서브타이핑
 * 
 * 어떤 클래스 A가 다른 클래스 B를 상속할 때, A를 B의 이름에 의한 서브타입(nominal subtype)이라고 말한다.
 * 스칼라는 구조적인 서브타이핑(structual subtyping)도 지원한다. 두 타입의 멤버가 같기 때문에 생기는 관계다.
 * 스칼라에서 구조적 서브타입을 사용하려면, 세분화한 타입(refinement type)을 사용하면 된다.
 * 
 * 무언가를 새로 설계하는 경우, 이름에 의한 서브타입 관계를 먼저 사용해야 한다. 이름은 짧은 식별자이므로, 타입을 모두
 * 나열하는 거보다 훨씬 간결하다.
 * 
 * 풀을 먹는 동물을 선언할 때, 이름에 의한 서브타입 보다, 세분화한 타입이 더 좋은 경우도 있다.
 * 
 * AnimalThatEatsGrass 라는 트레이트를 만들기 보다는,
 * Animal { Type SuitableFood = Graa }로, 기반클래스에서 멤버 타입을 더 자세히 지정한다.
 * 
 * 구조적 서브 타이핑이 좋은 또 다른 경우는, 다른 사람이 작성한 여러 클래스를 한꺼번에 그룹으로 다루고 싶을 때다.
 * 
 * 예를 들어 9.4절의 빌려주기 패턴을 일반화하고 싶다고 하자.
 * 
 * 원래는 PrintWriter 타입에 대해서만 작동했지만, close 메소드를 제공하는 모든 타입에 대해
 * 이 예제를 사용하고 싶다고 하자. 또한 열린 소켓을 정리하기를 바랄 수 있다.
 * 
 * 이 메소드는 연산을 수행하고, 객체를 닫기에, 연산과 대상 객체를 인자로 받아야 한다.
 */

class Pasture {
  var animals: List[Animal { type SuitableFood = Grass }] = Nil
  // ...
}
object c20_i08 extends App {
  /*
   * T가 close() 메소드를 지원한다는 사실을 명시하기 위해, <: 표현을 사용해 T에 대한 상위 바운드를 지정할 수 있다.
   * 
   * 기반 타입을 지정하지 않았기에 AnyRef를 자동으로 사용하며, AnyRef에는 결코 close 메소드가 없다는 것이다.
   * 기술적으로 말해서, 이런 경우의 타입({def close(): Unit})을 일컬어 구조적 타입이라 한다.
   */
  def using[T <: {def close(): Unit }, S](obj: T)(operation: T => S) = {
    val result = operation(obj)
    obj.close()
    result
  }
  using(new PrintWriter("date.txt")) { writer => writer.println(new Date) }
  //using(serverSocket.accept()) {socket => socket.getOutputStream().write("hello, world\n".getBytes) }
}