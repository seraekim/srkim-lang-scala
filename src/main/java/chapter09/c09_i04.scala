package chapter09

import java.io.PrintWriter
import java.io.File

/**
 * 9.4 새로운 제어 구조 작성
 *
 * 함수가 1급 계층인 언어에서는 언어의 문법이 고정되어 있더라도
 * 새로운 제어 구조를 작성할 수 있다. 함수를 인자로 받는 메소드만 있으면 된다.
 */
object c09_i04 extends App {
  /*
   * twice라는 제어 구조가 있다 가정하자.
   * op 인자로 함수값(_ + 1)을 받고, x 인자로 5를 받아서
   * 실제로 ((5 + 1) + 1) 연산이 이뤄진다.
   */
  def twice(op: Double => Double, x: Double) = op(op(x))
  println(twice(_ + 1, 5))

  /*
   * 이 메소드를 사용하는 경우, 파일 닫기를 보장한다는 장점이 있다.
   * 이러한 기법을 빌려주기 패턴(loan patter)이라고 함.
   * 
   * withPrintWriter 제어 추상화 함수가 자원을 열어 특정 함수에게 해당 자원을 빌려주기 때문.
   * withPrintWriter는 PrintWriter를 함수 op에 빌려준다.
   */
  def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  val file = new File("c09_i04_date.txt")
  withPrintWriter(file, srkim => srkim.println(new java.util.Date))
  
  /*
   * 코드를 작성하면서 좀 더 내장 제어 구조처럼 보이게 하는 방법 중 하나는
   * 인자 목록을 감쌀 때 소괄호가 아닌 중괄호를 사용하는 것이다.
   * 
   * 스칼라에서는 인자를 단 하나만 전달하는 경우 소괄호 대신 중괄호를 쓸 수 있다.
   * 
   * 왜 이렇게 했을까? 클라이언트 프로그래머가 중괄호 내부에 함수 리터럴을 사용하도록 하기 위해서다.
   * 커링을 활용해서 withPrintWriter을 재정의 해보자.
   */
  println {"인자 하나"}
  def withPrintWriter2(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  
  withPrintWriter2(file) {
    srkim => srkim.println(new java.util.Date)
  }
  
}