# jpashop

20220728

Entity

  -Member,Order,Delivery,Item,OrderItem,Address(Value Type) 개발
	
  -Enum[Category(값 미정),DeliveryStatus,OrderStatus] 개발
	
  -기술설명
    
    - @XToOne(fetch = FetchType.LAZY) : 연관관계 지연로딩 설정(필수), XToMany는 기본이 지연로딩

Repository

  -MemberRepository 개발
  
    -기능설명
    
      - save : 회원 객체 저장
      
      - findOne : member_id로 회원 검색
      
      - findAll : 모든 회원 검색
      
      - findByName : username으로 회원 검색
      
    -기술 설명

      -@Repository : 스프링 빈으로 등록

      -@RequiredArgsConstructor : final이 붙은 필드에 생성자 생성 및 @Autowired 대체
      
---------------------------------------------------------------------------------------------------------
      
 20220729
 
-Address 엔티티 zipcode 타입 String으로 변경 (기존 int타입 테스트시 null로 인한 예외 이슈)

-MemberRepositoryTest 삭제

-MemberServiceTest 생성 및 회원가입, 중복 회원 예외처리 테스트

-test폴더 apllication.yml 생성, 테스트 전용 In_Memory 사용
