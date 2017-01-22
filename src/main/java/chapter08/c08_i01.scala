package chapter08

import scala.io.Source

/**
 * 8장 함수와 클로저
 *
 * 스칼라는 자바에 존재하지 않는 함수 정의 방법을 몇 가지 제공한다.
 * 내포 함수, 함수 리터럴, 함수 값...
 *
 * 8.1 메소드
 *
 * 함수를 정의하는 가장 흔한 방법은 특정 객체의 멤버로 함수를 만드는 것이다.
 * 객체의 멤버인 함수를 메소드(method)라고 부른다.
 */
object c08_i01 extends App {
  /**
   * ------------------------------
   * 리스트 8.1
   */
  def processFile(filename: String, width: Int) {
    val lines = Source.fromFile("src/main/java/chapter07/" + filename).getLines().toList
    for (i <- 0 until lines.length)
      processLine(filename, i, width, lines(i))

    //for (line <- source.getLines())
    //  processLine(filename, width, line)
  }
  private def processLine(filename: String, lineNum: Int, width: Int, line: String) {
    if (line.length > width)
      println("(" + filename + ":" + lineNum + ") => " + line.trim)
  }
  //processFile("c07_i06.scala", 50)

  val args2 = "50" +: "c07_i06.scala" +: "c07_i01.scala" +: args
  val width = args2(0).toInt
  for (arg <- args2.drop(1))
    c08_i01.processFile(arg, width)
  
  var array = Array(1, 2, 3)
  array +:= 4
  //Array(4, 1, 2, 3)
  array :+= 0
  //Array(4, 1, 2, 3, 0)
}