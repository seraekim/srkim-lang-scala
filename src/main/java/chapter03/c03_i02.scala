package chapter03

/**
 * 8단계: 리스트를 사용해보자
 */
object c03_i02 {
  def main(args: Array[String]): Unit = {
    // 스칼라 List는 변경불가능 객체
    val o = List(1,2,3) //List[Int] 로 초기화
    // 변경없이, 새로운 List를 반환한다
    val o2 = List(1,2)
    val o3 = List(3,4)
    val o4 = o2 ::: o3
    // :: 콘즈는 새원소를 기존리스트의 맨 앞에 추가한다.
    val o5 = 1 :: o3
    // 연산자에 대해서 : 콜론의 경우라면 :메소드의 호출객체는 오른쪽 이다 따라서 o3::(1) 이라고 볼 수 있다.
    // 빈 리스트를 Nil 이라 함.
    val o6 = 1::2::3::Nil //List(1,2,3) 와 같다.
    
    // 왜 리스트 뒤에 추가하지 않을까?
    // :+ 를 사용할수 있긴 하다. 하지만 뒤에 추가하는 것은 리스트 길이 비례한 시간이 걸린다.
    // :: 를 사용하는 것은 상수 시간이 걸린다.
    // 따라서 효율적으로 추가할 것이면 뒤집은 다음에 추가하고 돌려놓는다. 또는 ListBuffer를 쓴다.
    
    val thrill = "Will"::"fill"::"until"::Nil
    var es = ""
    //println(thrill(3))
    println(ln+" : "+thrill.count { x => x.length() == 4 }) // 문자열 길이가 4인 개수
    println(ln+" : "+thrill.drop(2)) // 처음 두 원소 없앰
    println(ln+" : "+thrill.dropRight(2)) // 맨 오른쪽 두 원소 없앰
    println(ln+" : "+thrill.exists { x => x == "until" }) // true
    println(ln+" : "+thrill.filter { x => x.length == 4 }) // 문자열 길이 4인 원소 반환
    println(ln+" : "+thrill.forall { x => x.endsWith("l") }) // 문자열이 모두 ㅣ 로 끝나니 true
    thrill.foreach { x => print(ln+" : "+x) } //thrill.foreach { print }
    println()
    println(ln+" : "+thrill.head) // 첫번째 원소
    println(ln+" : "+thrill.last) // 마지막 원소
    println(ln+" : "+thrill.isEmpty) // 비어있는지 여부 확인
    println(ln+" : "+thrill.length) // 길이
    println(ln+" : "+thrill.map { x => x+"y" }) // 문자열 뒤에 y 추가
    println(ln+" : "+thrill.mkString(", ")) // 원소를 문자열로 만든다.
    // List had a remove method in earlier versions, but it has been deprecated in 2.8 
    // and removed in 2.9. Use filterNot instead.
    println(ln+" : "+thrill.filterNot { x => x.length == 4 }) // 길이가 4인 것을 제외하고..
    println(ln+" : "+thrill.reverse) // 역순 반환
    println(ln+" : "+thrill.sortWith { (s, t) => s.charAt(0).toLower < t.charAt(0).toLower }) // 소문자상태에서 정렬
    
    println(ln+" : "+thrill.init) // 마지막 원소 제외하고
    println(ln+" : "+thrill.tail) // 첫번째 원소를 제외하고
    
  }
  def ln() = Thread.currentThread().getStackTrace()(2).getLineNumber
}