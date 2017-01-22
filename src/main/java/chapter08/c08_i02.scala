package chapter08

import scala.io.Source

/**
 * 8.2 지역 함수
 *
 * 재사용을 위해 함수를 클래스와 오브젝트로 패키징하려면,
 * 클래스를 사용하는 측에 대해 도우미 함수들을 감추는게 바람직하다.
 *
 * 도우미 함수는 하나의 개별 단위로는 의미가 없고, 설계자가 나중에 도우미 함수를 지우고 다른 방법으로
 * 클래스를 작성해도 문제가 없을 정도로 유연하기를 원하기 때문
 *
 * 자바에서는 주로 private을 쓰는데, 스칼라에서는 다른 방법도 가능하다.
 * 바로 함수안에 함수(지역 함수)를 정의하는 것으로 블록 밖에서는 접근이 불가능해진다.
 */
object c08_i02 extends App {
  def processFile(filename: String, width: Int) {
    def processLine(line: String) {
      // 지역 함수는 바깥 스코프의 변수 width, filename을 인자로 안 받아도 접근이 가능하다.
      if (line.length > width)
        println(filename + ": " + line)
    }
    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      processLine(line)
    }
  }
}