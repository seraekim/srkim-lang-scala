package chapter03

import scala.io.Source

/**
 * 12단계: 파일의 내용을 줄 단위로 읽자
 */
object c03_i06 {
  def widthOfLength(s: String) = s.length().toString().length()
  def main(args: Array[String]): Unit = {
    val fileArr = Array("src/chapter03/c03_i06.scala")
    if (fileArr.length > 0) {
      val lines = Source.fromFile(fileArr(0)).getLines().toList
      val longestLine = lines.reduceLeft((a, b) => if (a.length() > b.length()) a else b)
      val maxWidth = widthOfLength(longestLine)
      for (line <- lines) {
        val numSpaces = maxWidth - widthOfLength(line)
        val padding = " " * numSpaces
        println(padding + line.length + " | " + line)
      }
    } else {
      Console.err.println("Please Enter filename")
    }
  }
}