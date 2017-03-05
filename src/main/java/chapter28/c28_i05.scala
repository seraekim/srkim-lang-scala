package chapter28

/**
 * 28.5 XML 분석
 */
object c28_i05 extends App {
  /*
   * 1. 텍스트 추출
   * 
   * XML노드의 text 메소드를 호출 시, 그 노드 안의 모든 텍스트를 가져올 수 있다.
   */
   <a>Sounds <tag/> good</a>.text          //> String = Sounds  good
   <a>Sounds <tag>ss</tag> good</a>.text   //> String = Sounds ss good
   <a> input ---&gt; output </a>.text      //> String = " input ---> output "
   
   /*
    * 2. 하위 엘리먼트 추출
    * 
    * 
    */
}