package chapter07

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

/**
 * 7.4 try 표현식으로 예외 다루기
 */
object c07_i04 extends App {

  /*
   * 1. 예외 발생시키기
   */
  val n = 2 //3
  val half =
    if (n % 2 == 0)
      n / 2
    else
      /*
       * 모순처럼 보일 수 있는데, 스칼라에서는 throw가 결과 타입이 있는 표현식이다.
       * 어차피 어떤 문맥내에서 throw가 던지는 예외를 사용하려 해도 결과(예외)를 사용할 수 있는 경우는 
       * 없기 때문에 이렇게 처리해도 아무 문제가 없다.
       * 
       * throw 표현식의 결과 타입은 Nothing 이다.
       * 그런데 기술적으로 예외가 발생하면 if 는 결과값을 내놓지 않고 예외발생쪽으로 흐름이 흘러간다.
       * 그럼에도 불구하고 굳이 throws를 표현식이라 하고 결과타입을 정한 것은
       * if 의 두 분기는 둘다 표현식이어야, throw를 자유롭게 쓸 수 있기 때문이며, 문법 조화도 이루어지기 때문이다.
       * 
       * 결론은 throw가 스칼라에서 표현식인 이유는 자유롭게 쓸 수 있도록 한 기술적 장치인 것이다. 
       */
      throw new RuntimeException("n must be even")
  println(half)

  /*
   * 2. 발생한 예외 잡기
   * 
   * catch 절에서 예외를 잡지 못하면, try-catch가 끝나고 예외는 계속 전파된다.
   * 자바와 눈에 띄게 다른점은, 자바라면 checked exception을 꼭 처리해줘야만 하는데
   * 스칼라는 그럴 필요가 없다. @throws 를 활용해도 된는데 31.2절에서 설명한다.
   */
  try {
    val f = new FileReader("src/main/java/chapter07/c07_i04.scala")
    //val f = new FileReader("src/main/java/chapter07/input.scala")
    // 파일을 사용하고 닫는다.
  } catch {
    case ex: FileNotFoundException =>
      println("파일을 못 찾는 경우 처리")
    case ex: IOException =>
      println("그 밖의 IO 오류 처리")
  }

  /*
   * 3. finally 절
   * 
   * 예외가 발생해 메소드를 빠져나가더라도 열어둔 파일을 닫고 싶은 경우 사용
   * 자바에서 흔히 볼 수 있는 메모리 누수 처리  패턴인데 사실 스칼라는 더 간결하게 표현가능하다.
   * 빌려주기 패턴(loan pattern) 기법은 9.4절 참고
   */
  val file = new FileReader("src/main/java/chapter07/c07_i04.scala")
  try {

  } finally {
    file.close()
  }

  /*
   * 4. 값 만들어내기
   * 
   * 대부분의 스칼라 제어 구조와 마찬가지로 try-catch-finally도 결과는 값이다.
   * try에서 성공하면 try의 값을
   * catch에서 성공하면 catch의 값을
   * 예외 발생하였으나 catch에서 처리 못한다면, finally 절이 실행되나
   * finally 는 try 나 catch에 의한 값을 바꾸지 말아야 하며, 값은 버려진다.
   */
  def urlFor(path: String) =
    try {
      new URL(path)
    } catch {
      case e: MalformedURLException =>
        new URL("http://www.scala-lang.org")
    }
  // 다소 억지스런 함수 정의 finally의 값
  def f(): Int = try { return 1 } finally { return 2 }
  println(f)
  // 이게 정당한 로직. finally의 값은 무시 된다.
  // finally 는 값을 내놓기 보단 부수효과를 처리해야 한다.
  def g(): Int = try  1  finally  2 
  println(g)
}