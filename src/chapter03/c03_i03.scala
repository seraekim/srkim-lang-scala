package chapter03

/**
 * 9단계: 튜플을 사용해보자
 * 튜플은 또 다른 유용한 컨테이너 객체다. 리스트와 마찬가지로 변경 불가능
 * 그러나 각기 다른 타입의 원소를 넣을 수 있음
 * 
 */
object c03_i03 {
  def main(args: Array[String]): Unit = {
    val pair = (99, "Luftballons")
    val pair2: Tuple2[Int, String] = (11,"ss")
    val pair3: Tuple6[Char, Char, String, Int, Int, String] = ('u','r',"the",1,4,"me")
    /*
     * List(0) 과 같이 호출은 불가능하다.
     * pair(0)??? 이유는 타입이 바뀌기 때문이다.
     * 
     * 또한 하스켈 ML 전통을 따라서 인덱스는 1부터 시작한다.
     */
    println(pair._1)
    println(pair._2)
  }
}