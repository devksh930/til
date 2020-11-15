# SPRING HATEOAS

### <b>H</b>ypermedia <b>A</b>s <b>T</b>he <b>E</b>ngine <b>O</b>f <b>A</b>pplication <b>S</b>tate

- 서버 : 현재 리소스와 연관된 링크 정보를 클라이언트에게 제공한다.
- 클라이언트 : 연관된 링크정보를 바탕으로 리소스에 접근
- 연관된 링크 정보
> - Relation(나자신)
> - Hypertext Reference(Href)(연관된 링크정보)

#### ObjectMapper 제공
- 제공하는 리소스를 json으로 반환할때 사용하는 인터페이스
- spring.jackson.* 에서 제공
- spring-webMVC만 넣더라도 빈으로 등록해

#### LinkDiscovers 제공
- 클라이언트 쪽에서 링크정보를 Rel 이름을 찾을때 사용할 수 있는 XPath 확장 클래스

### spring2.2 이후 HATEOAS 변화
- Resource -> EntityModel
- Resources -> CollectionModel
- PagedResrouces -> PagedModel
- ResourceSupport -> RepresentationModel
- assembler.toResource -> assembler.toModel
- org.springframework.hateoas.mvc.ControllerLinkBuilder -> org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
- MediaTypes 중에 (UTF8)인코딩이 들어간 상수 제거.