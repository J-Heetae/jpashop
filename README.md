# jpashop

20220728

Entity
  -Member,Order,Delivery,Item,OrderItem,Address(Value Type) 개발
  -Enum[Category(값 미정),DeliveryStatus,OrderStatus] 개발
  -기술설명
    -@XToOne(fetch = FetchType.LAZY) : 연관관계 지연로딩 설정(필수), XToMany는 기본이 지연로딩

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
      
