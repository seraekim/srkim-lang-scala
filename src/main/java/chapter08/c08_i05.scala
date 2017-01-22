package chapter08

/**
 * 8.5 위치 표시자 문법
 * 
 * 함수 리터럴을 좀 더 간결하게 만들기 위해 "밑줄"을 하나 이상의 파라미터에 대한
 * 위치 표시자로 사용한다.
 * 
 * 단, 함수 리터럴에서 각 인자는 한 번씩만 나타나야 한다.
 */
object c08_i05 extends App {
  val someNumbers = List(-11, -10, 0, 5, 10)
  someNumbers.filter(_ > 0)
  
  //인자의 타입정보를 못찾으면 타입을 명시해주면 그만이다.
  //val f = _ + _
  val f = (_:Int) + (_:Int)
  //val f2 = (x: Int,y: Int) => x + y
  
  f(5,5)
}