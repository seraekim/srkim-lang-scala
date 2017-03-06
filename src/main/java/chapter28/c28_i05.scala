package chapter28

/**
 * 28.5 XML 분석
 */
object c28_i05 extends App {
  /*
   * 
   */
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
   * 태그 이름으로 하위 엘리먼트를 검색하고 싶다면, \에 태그명을 넘기면 된다.
   * \\ 연산자를 사용하면 깊은 검색을 통해 하위 엘리먼트 등도 가져올 수 있다.
   */
  val xml = <a><b><c>hello</c></b></a>           //> xml  : scala.xml.Elem = <a><b><c>hello</c></b></a>
  xml \ "b"                                      //> res0: scala.xml.NodeSeq = NodeSeq(<b><c>hello</c></b>)
  xml \ "c"                                      //> res1: scala.xml.NodeSeq = NodeSeq()
  xml \\ "c"                                     //> res2: scala.xml.NodeSeq = NodeSeq(<c>hello</c>)
  xml \ "a"                                      //> res3: scala.xml.NodeSeq = NodeSeq()
  xml \\ "a"                                     //> res4: scala.xml.NodeSeq = NodeSeq(<a><b><c>hello</c></b></a>)

  /*
   * 2. 애트리뷰트 추출
   * 
   * 태그의 애트리뷰트를 \ 메소드를 사용해 가져올 수 있다. 다만 애트리뷰트 이름 앞에 @를 붙여야 한다.
   */
  val joe = <employee name="Joe" nk="code monkey" serial="123" />
                                                  //> joe  : scala.xml.Elem = <employee name="Joe" nk="code monkey" serial="123"/>
                                                  //| 
  joe \ "@name"                                   //> res0: scala.xml.NodeSeq = Joe
  joe \ "@serial"                                 //> res1: scala.xml.NodeSeq = 123
  
}