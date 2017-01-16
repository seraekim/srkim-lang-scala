package chapter04
import c04_i03.calculate
/**
 * 4.5 Application 트레이트
 * 
 * App 트레이트 안에 적절한 시그니처의 main 메소드가 정의 됨
 * 
 * 기존 Application은 다음의 3가지 문제가 있다.
 *  - args 인자를 main으로 받아서 활용하는 경우에는 App 쓰면 안됨
 *  - 다중스레드로 동작한다면 main을 직접 작성해야 함
 *  - 객체 초기화 코드를 최적화 하지 않는다.
 * 
 * 그러나 scala.App 은 3가지 단점을 해결했다.
 */
object c04_i05 extends App {
  println(args.length)
  for (season <- List("fall","winter","spring"))
    println(season +": "+ calculate(season))
}