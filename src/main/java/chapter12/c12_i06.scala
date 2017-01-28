package chapter12

/**
 * 12.6 왜 다중 상속은 안 되는가?
 * 
 * 답은 선형화다.
 * 
 * 클래스를 new를 이용해 인스턴스화 할 때 스칼라는 클래스 자신, 조상 클래스들, 믹스인한 트레이트를
 * 한 줄로 세워 순서를 정한다.그리고 super를 클래스 내부에서 호출 시, 해당 순서에서 한 단계 다음에
 * 있는 메소드를 호출한다. 마지막에 있는 클래스(최상위)의 메소드를 제외한 모든 메소드에서 super를 호출하면
 * 결과적으로 여러 동작을 쌓아올리게 된다.
 * 
 * 자세한 선형화 순서는 언어 명세이 있다. 클래스 자인은 가장 앞에 위치하므로, super를 호출하는
 * 메소드를 만들어서 트레이트의 동작을 변화시킬 수 있다. 그러나 반대는 동작하지 않는다.
 */
object c12_i06 {
  
}

class Animal2
trait Furry extends Animal2
trait HasLegs2 extends Animal2
trait FourLegged extends HasLegs2
/*
 * 선형화 순서, super 호출 해석 시 진행하는 방향 순서
 * Animal     | -> AnyRef -> Any
 * Furry      | -> Animal -> AnyRef -> Any
 * HasLegs    | -> Animal -> AnyRef -> Any
 * FourLegged | -> HasLegs -> Animal -> AnyRef -> Any
 * Cat        | -> FourLegged -> HasLegs ->  Furry -> Animal -> AnyRef -> Any
 */
class Cat extends Animal2 with Furry with FourLegged