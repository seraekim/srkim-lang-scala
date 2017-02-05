package chapter17

/**
 * 17.5 튜플
 * 
 * 정해진 개수의 원소를 한데 묶는다.
 * 
 * 단순 데이터만 저장하는 클래스를 정의해야하는 번거로움을 덜고 튜플을 쓰자.
 * 튜플은 타입이 각기 다른 객체를 결합할 수 있기 때문에 Traversable을 상속하지 않는다.
 * 정확히 정수 하나와 문자열 하나를 묶고 싶다면 리스트나 배열이 아니라 튜플이 필요하다.
 */
object c17_i05 extends App {
  (1, "hello", Console)
  
  // 튜플을 사용하는 가장 일반적인 경우는 메소드에서 여러 값을 반환하는 것이다.
  def longestWord(words: Array[String]) = {
    var word = words(0)
    var idx = 0
    for (i <- 1 until words.length)
      // 아래 분기 조건에서, 가장 긴 단어들의 길이가 같은 경우 가장 처음 단어가 반환됨을 알 수 있다.
      // 뒤의 단어가 나오게하려면 >=...
      if(words(i).length > word.length) {
        word = words(i)
        idx = i
      }
    (word, idx)
  }
  val longest = longestWord("The quick brown fox".split(" "))
  println(longest)
  
  longest._1
  longest._2
  
  // 원소에 따로 할당
  val (w, i) = longest
  // 만약 괄호 생략하면 변수 하나하나에 longest 전체가 할당
}