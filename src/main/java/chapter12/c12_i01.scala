package chapter12

/**
 * 12장 트레이트
 * 
 * 스칼라에서 트레이트는 코드 재사용의 근간을 이루는 단위다.
 * 
 * 사용 패턴은 크게 두 가지가 있다.
 *  - thin interface -> rich interface
 *  - 쌓을 수 있는 변경 stackable modification 정의
 *  
 *  12.1 트레이트의 동작 원리
 *  
 *   - 트레이트는 클래스 파라미터를 가질 수 없다. 즉 주 생성자에 전달할 파라미터를
 *     트레이트 정의에 넣을 수 없다.
 *   - 클래스는 super 호출을 정적으로 바인딩하지만, 트레이트에서는 동적으로 바인딩한다.
 */
object c12_i01 extends App {
  val frog = new Frog
  frog.philosophize()
  /*
   * phil 변수의 타입은 Philosophical 트레이트 이기에, Philosophical
   * 트레이트를 믹스인한 어떤 객체로도 초기화할 수 있다.
   */
  val phil: Philosophical = frog
  phil.philosophize()
}

/*
 * 클래스 만드는 방식과 같다. 앞에 trait 써준다.
 * 부모 클래스 지정이 없으니 AnyRef가 부모 클래스다.
 */
trait Philosophical {
  def philosophize() {
    println("I consume memory, therefore I am !")
  }
}

/*
 * extends 키워드로 트레이트를 믹스인한 클래스
 */
class Frog extends Philosophical {
  override def toString = "green"
}

/*
 * 여러 트레이트 믹스인.. with 키워드
 */
class Animal
trait HasLegs
class Frog2 extends Animal with Philosophical with HasLegs {
  override def toString = "green2"
  override def philosophize() {
    println("It ain't easy being "+toString+"!")
  }
}