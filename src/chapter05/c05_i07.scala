package chapter05

/**
 * 5.7 객체 동일성
 */
object c05_i07 {
  def main(args: Array[String]): Unit = {
    /*
     * ==
     * !=
     * 
     * 좌항이 null 이 아니라면 해당 객체의 equals 메소드를 호출한다.
     */
    println(("he"+"llo")=="hello")
    
  }
}