package chapter24

/**
 * 24.5 시퀀스 트레이트: Seq, IndexedSeq, LinearSeq
 * 
 * Seq 트레이트는 시퀀스를 표현한다. 시퀀스는 길이가 정해져 있고, 각 원소의 위치를 0부터 시작하는
 * 정해진 인덱스로 지정할 수 있는 일종의 Iterable이다.
 * 
 *  - 인덱스와 길이 연산: apply, isDefinedAt, length, indices, lengthCompare.
 *  Seq의 경우 apply는 인덱스로 원소를 찾는 것을 의미한다. Seq[T] 타입 시퀀스는 Int를 받아서
 *  T 타입의 원소를 돌려주는 부분 함수다. 즉, Seq[T]는 PartialFunction[Int, T]를 확장한다.
 *  시퀀스에 있는, length 메소드는 일반적인 컬렉션에 있는 size 메소드에 대한 별칭이다. lengthCompare
 *  메소드를 사용하면 어느 한쪽의 길이가 무한하더라도 두 시퀀스의 길이를 비교할 수 있다.
 *  
 *  - 인덱스 찾기 연산: indexOf, lastIndexOf, indexOfSlice, lastIndexOfSlice,
 *  indexWhere, lastIndexWhere, segmentLength, prefixLength. 주어진 값과 같거나
 *  어떤 술어(조건 함수)를 만족하는 원소의 인덱스를 반환한다.
 *  
 *  - 추가 연산: +:, :+, padTo. 시퀀스의 맨 앞이나 뒤에 원소를 추가한 새 시퀀스를 반환 한다.
 *  - 변경 연산: updated, patch. 원래의 시퀀스 일부 원소를 바꿔서 나오는 새로운 시퀀스를 반환
 *  - 정렬 연산: sorted, sortWith, sortBy는 시퀀스 원소들을 여러 기준에 따라 정렬한다.
 *  - 반전 연산: reverse, reverseIterator, reverseMap. 시퀀스의 원소를 역순, 즉 마지막에서
 *  맨 앞쪽으로 처리하거나 역순으로 토해낸다.
 *  
 */
object c24_i05 {
  
}