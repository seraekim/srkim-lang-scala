package chapter24

/**
 * 24.11 배열
 * 
 * 스칼라 컬렉션에서 배열은 특별하다. 자바 배열과 일대일로 대응한다. 스칼라 Array[Int]는 자바
 * int[]으로 표현할 수 있다는 뜻이다. 하지만 동시에 자바 쪽의 같은 배열에 비해 훨씬 많은 기능을 제공한다.
 * 
 *  - 1. 스칼라 배열은 제네릭 하다. Array[T]
 *  - 2. 스칼라 배열은 스칼라 시퀀스와 호환 가능하다. Array[T]를 Seq[T]가 필요한 곳에 넘길 수 있다.
 *  - 3. 스칼라 배열은 모든 시퀀스의 연산을 지원한다.
 */
object c24_i11 extends App {
  val a1 = Array(1,2,3)                           //> a1  : Array[Int] = Array(1, 2, 3)
  val a2 = a1 map (_ * 3)                         //> a2  : Array[Int] = Array(3, 6, 9)
  val a3 = a2 filter ( _ % 2 != 0)                //> a3  : Array[Int] = Array(3, 9)
  a3.reverse                                      //> res0: Array[Int] = Array(9, 3)
  /*
   * 스칼라 배열을 자바 배열과 마찬가지로 표현한다는 사실을 생각해볼 때, 어떻게 이런 추가 기능을
   * 스칼라에서 지원할 수 있을까 신기할 것이다. 스칼라 2.8부터는 배열은 결코 시퀀스인 척하지 않는다.
   * 네이티브 배열을 표현하는 데이터 타입이 Seq의 하위 타입이 아니기 때문에 실제로 그럴 수도 없다.
   * 배열을 scala.coleciton.mutable.WrappedArray 클래스로 바꿔주는 암시적
   * 감싸기 변환이 존재한다. WrappedArray는 Seq의 서브 클래스다.
   * 
   * Array 에서 WrappedArray로의 암시적 변환이 있어서 배열이 시퀀스 역할을 하며, 반대방향으로 
   * 가기위해서는 Traversable에 있는 toArray 메소드를 사용할 수 있다.
   */
  val seq: Seq[Int] = a1                          //> seq  : Seq[Int] = WrappedArray(1, 2, 3)
  val a4: Array[Int] = seq.toArray                //> a4  : Array[Int] = Array(1, 2, 3)
  a1 eq a4                                        //> res1: Boolean = true
  
  /*
   * seq는 WrappedArray이며 reverse도 WrappedArray로 돌려준다. 이게 논리적이다.
   */
  seq.reverse                                     //> res2: Seq[Int] = WrappedArray(3, 2, 1)
  val ops: collection.mutable.ArrayOps[Int] = a1  //> ops  : scala.collection.mutable.ArrayOps[Int] = [I(1, 2, 3)
  ops.reverse                                     //> res3: Array[Int] = Array(3, 2, 1)
  /*
   * 반면 ArrayOps의 reverse는 Seq가 아니고 Array이다. ArrayOps를 이처럼 쓰지는 않는다.
   * 보통은 a1.reverse 와 같이 배열에 대해 Seq메소드를 직접 호출한다.
   * 
   * ArrayOps 객체는 암시적 변환에 의해 자동 삽입된다.
   */
  a1.reverse                                      //> res4: Array[Int] = Array(3, 2, 1)
  // 실제 다음이 자동 적용된 것이다.
  intArrayOps(a1).reverse                         //> res5: Array[Int] = Array(3, 2, 1)
  /*
   * 이제 드는 의문은 어떻게 컴파일러가 앞의 코드를 보고 WrappedArray 대신 intArrayOps를 선택하느냐다.
   * 전자는 Predef 객체, 후자는 prefer 슈퍼클래스인 scala.LowPriorityImplicits에 들어있다.
   * 서브클래스의 암시는 슈퍼클래스의 암시보다 더 우선적으로 쓰인다.
   * 
   * 이제 배열이 시퀀스와 호환 가능한 이유와 배열이 모든 시퀀스 연산을 지원할 수 있는 이유를 알았다. 그렇다면  자바에서는
   * T[]가 불가능한데 스칼라에서는 Array[T]가 가능한 이유는 무엇일까?
   * 
   * 실행시점에서 자바의 여덟가지 원시 타입 배열 똔느 객체의 배열이 될 수 있는데, 이 모두를 꿰뚫는 공통 실행 시점 타입은
   * AnyRef(=java.lang.Object)뿐이다. 따라서 스칼라는 Array[T]를 AnyRef의 배열로 변환한다.
   * 
   * 제네릭 배열 타입을 표현하는 것 뿐만 아니라 만들어낼 방법도 필요하다.
   * 
   * 타입 파마리터 T에 연관된 실제 타입은 실행 시점에 지워진다.
   */
  /*
  def evenElems[T](xs: Vector[T]): Array[T] = {
    val arr = new Array[T]((xs.length + 1) / 2) // cannot find class tag for element type T
    for (i <- 0 until xs.length by 2)
      arr(i / 2) = xs(i)
    arr
  }
  */
  /*
   * 여기서 필요한 건 evenElems의 실제 타입 파라미터가 어떤 것이 될 수 있는지에 대한 런타임 힌트를 컴파일러에게 제공하는 것이다.
   * scala.reflect.ClassManifest의 형태를 취한다. 클래스 매니페스트는 해당 타입의 루트 슈퍼클래스가
   * 어떤 것이 될지를 설명하는 타입기술자(type descriptor)객체다. 클래스 매니페스트 대신에 scala.reflect.Manifest
   * 라는 전체 매니페스트도 있다. 이는 어떤 타입의 모든 측면을 기술하는데, 배열을 생성할 때는 클래스 매니페스트만 필요하다.
   * 
   * 스칼라 컴파일러는 지시에 따라 클래스 매니페스트를 만들어서 전달하는 코드를 자동으로 생성하는데 지시라는 말은
   * 암시적 파라미터로 요청한다는 뜻이다.
   * def evenElems[T](xs: Vector[T])(implicit m: ClassManifest[T]): Array[T] = ...
   * 
   * 컨텍스트 바운드를 사용해 더 짧은 문법을 쓸 수도 있다. 타입 파라미터 뒤에 콜론과 클래스 이름을 추가하면 된다.
   * 2.10.0 이후로 ClassManifest 대신에 scala.reflect.ClassTag을 쓰고 있다.
   * 
   * 컴파일러가 Array[T]를 만들 때 타입 파라미터 T에 대한 클래스 매니페스트를 찾을 것이다.
   */
  def evenElems[T: scala.reflect.ClassTag](xs: Vector[T]): Array[T] = {
    val arr = new Array[T]((xs.length + 1) / 2) // cannot find class tag for element type T
    for (i <- 0 until xs.length by 2)
      arr(i / 2) = xs(i)
    arr
  }                                               //> evenElems: [T](xs: Vector[T])(implicit evidence$2: scala.reflect.ClassTag[T
                                                  //| ])Array[T]
  
  evenElems(Vector(1,2,3,4,5))                    //> res6: Array[Int] = Array(1, 3, 5)
  evenElems(Vector("this","is","a","test","run")) //> res7: Array[String] = Array(this, a, run)
  /*
   * 제네릭 배열 생성 시에는 클래스 매니페스트가 필요하다. 가장 쉬운 방법은 컨텍스트 바운드를 사용해 타입 파라미터를 선언하는 것이다.
   */
}