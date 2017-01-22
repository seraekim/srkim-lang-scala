package chapter08

/**
 * 8.8 특별한 형태의 함수 호출
 * 
 *  - 반복 파라미터(repeated parameter)
 *  - 이름 붙인 인자(named argument)
 *  - 디폴트 인자(default argument)
 */
object c08_i08 extends App {
  /*
   * 1. 반복 파라미터
   * 
   * 길이가 변하는 인자 목록을 함수에 전달 할 수 있다. (0개 이상)
   * 별표를 인자의 타입 다음에 추가하면 된다.
   * 
   * 함수내부에서 반복 파라미터의 타입은 지정한 파라미터 타입의 배열이다.
   * String* = Array[String]
   * 
   */
  def echo(args: String*) = for (arg <- args) println(arg)
  
  echo()
  echo("one")
  echo("hello", "world!")
  
  /*
   * 직접 배열로 전달하면 컴파일 오류가 발생한다.
   * 배열을 반복 인자로 보내려면 콜론에 _* 기호를 추가해야 한다.
   */
  val arr = Array("What's","up","doc?")
  echo(arr: _*)
  
  /*
   * 2. 이름 붙인 인자
   * 
   * 파라미터 목록에 정해진 순서와 다른 순서로 함수에 인자를 전달하게 해준다.
   * 각 인자앞에 이름과 등호 표시만 해주면 된다.
   * 
   * 위치기반 인자와 이름 붙인 인자를 혼용할 수 있는데 이 경우
   * 위치기반 인자를 먼저 써준다.
   */
  def speed(distance: Float, time: Float): Float = distance/time
  println(speed(100, 10))
  println(speed(time=10,distance=100))
  
  /*
   * 3. 디폴트 인자 값
   * 
   * 파라미터의 디폴트 값을 지정한다.
   * 이름 붙인 인자와 혼용할 수 있다.
   */
  // Console.out 으로 디폴트 인자가 정해짐
  def printTime(out: java.io.PrintStream = Console.out, divisor: Int = 1) = 
    out.println("time = "+System.currentTimeMillis()/divisor)
  printTime(out = Console.err)
  printTime(divisor = 1000)
  printTime(divisor = 10)
  
}