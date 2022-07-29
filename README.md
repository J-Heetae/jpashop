# jpashop

20220728

  -Member,Order,Delivery,Item,OrderItem,Address(Value Type) 개발
	
  -Enum[Category(값 미정),DeliveryStatus,OrderStatus] 개발

  -MemberRepository 개발

	-MemberService 개발

---------------------------------------------------------------------------------------------------------
      
20220729
 
	-Address 엔티티 zipcode 타입 String으로 변경 (기존 int타입 테스트시 null로 인한 예외 이슈)

	-MemberRepositoryTest 삭제

	-MemberServiceTest 생성 및 회원가입, 중복 회원 예외처리 테스트

	-test폴더 apllication.yml 생성
