package chapter07

import java.io.InputStreamReader
import java.io.BufferedReader
import scala.util.control.Breaks
//import scala.util.control.Breaks._

/**
 * 7.6 break와 continue 문 없이 살기
 * 
 * 8장에서 설명할 함수 리터럴과 어울리지 않기 때문에 스칼라에서는
 * break와 continue를 제외했다.
 * 
 * while 루프 내에서 continue가 지니는 의미는 명확하지만, 함수리터럴 에서는?
 * 
 * 가장 간단한 접근법은 모든 continue 문을 if로, 모든 break 문을 불리언 변수로 대체
 */
object c07_i06 extends App {
  // 자바에서 while, break, continue를 좋아한다면 다음과 같다.
  // 인자목록에서 -로 시작하지 않고, .scala로 끝나는 문자열을 검색한다고 가정
  /*int i = 0;
  boolean foundIt = false;
    
  while ( i<args.length) {
    if ( args[i].startsWith("-")) {
      i = i + 1;
      continue;
    }
    if(args[i].endsWith(".scala")) {
      foundIt = true;
      break;
    }
    i = i + 1;
  }*/
  
  // 자바코드를 스칼라로 그대로 옮기려면 while 루프의 나머지 전체를 감싸는 if 문을 작성
  var i = 0
  var foundIt = false
  
  while (i<args.length && !foundIt) {
    if(!args(i).startsWith("-")) {
      if(args(i).endsWith(".scala"))
        foundIt = true
    }
    i = i + 1
  }
  
  /*
   * var 제거하고 싶다면 재귀함수
   * continue i+1 넘기는 루프를 재귀함수로 바꿈 searchFrom(i+1)
   * 코드상으로 재귀지만.. 실제 스칼라 컴파일러는 이 코드에 대해 재귀함수를 만들어내지 않는다.
   * 모든 재귀호출이 꼬리 재귀 호출이기 때문에 컴파일러는 while 루프와 비슷한 코드를 만들어낸다.
   * 각 재귀호출은 함수 시작 부분으로 가는 점프로 바뀐다. 꼬리 재귀 최적화에 대해서는 8.9절에서 다룸.
   */
  def searchFrom(i: Int): Int =
    if (i >= args.length) -1
    else if ( args(i).startsWith("-")) searchFrom(i+1)
    else if ( args(i).endsWith(".scala")) i
    else searchFrom(i+1)
    
  val i2 = searchFrom(0)
  
  // 지금까지 읽고도 여전히 break가 필요하다고 생각하는 독자가 있다면, 스칼라 표준라이브러리 Breaks 클래스를 써라.
  
  val in = new BufferedReader(new InputStreamReader(System.in))
  // Breaks 클래스는 break에서 예외를 던지고, 바깥의 breakable 메소드에서 그 예외를 잡는 방법으로 이를 구현한다.
  // 심지어 break 호출이 breakable을 호출한 메소드와 같지 않아도 된다.
  Breaks.breakable {
    while(true) {
      println("? ")
      if (in.readLine() == "") Breaks.break
    }
  }
}