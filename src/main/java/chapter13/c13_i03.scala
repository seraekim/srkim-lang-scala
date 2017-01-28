package chapter13

/**
 * 13.3 임포트
 * 
 * 패키지와 그 멤버는 import 절을 통해 불러올 수 있다.
 * 
 * _밑줄은 모든 멤버에게 접근
 */
abstract class Fruit(val name: String, val color: String)
object Fruits {
  object Apple extends Fruit("apple","red")
  object Orange extends Fruit("orange","orange")
  object Pear extends Fruit("pear","yellowish")
  val menu = List(Apple, Orange, Pear)
  
  /*
   * 스칼라 임포트는 컴파일 단위의 시작 부분뿐 아니라 코드의 어디에라도 들어간다.
   * fruit의 멤버를 임포트하여 name, color를 바로 쓴다.
   * 특히 객체를 모듈로 사용할 때 이 문법이 유용하다.
   */
  def showFruit(fruit: Fruit) {
    import fruit._
    println(name +"s are "+ color)
  }
  /*
   * 패키지 이름 불러오기
   */
  import java.util.regex
  def AstarB {
    val pat = regex.Pattern.compile("a*b")
  }
  
}

// 자바의 싱글 타입 임포트(single type)
//import chapter13.Fruit
// 자바의 주문식 임포트(on-demand)
import chapter13._
// 자바의 정적 클래스 필드를 불러오는 임포트
//import chapter13.Fruits._

/*
 * 스칼라 임포트는 멤버를 감추거나 이름을 바꿀 수 있다. import selecter(중괄호)사용.
 */
import Fruits.{Apple, Orange}
/*
 * Apple의 이름을 바꾼다.
 */
import Fruits.{Apple => McIntosh, Orange}
/*
 * sql 과 자바의 날짜 캘래스를 구분한다면 다음처럼 할 수도 있다.
 */
import java.sql.{Date => SDate}
import java.{sql => S} // S.Date

import Fruits.{_} // Fruits._와 동일
/*
 * 모든 멤버를 불러오나 Apple의 이름만 변경
 */
import Fruits.{Apple => McIntosh, _}
/*
 * 모든 멤버를 불러오나 Pear만 제외(숨김), ~ => _
 * 다른 패키지와의 모호함을 피할 때 유용하게 쓰인다.
 * 또한 모든 멤버에 해당하는 _는 마지막에 온다.
 */
import Fruits.{Pear => _, _}


