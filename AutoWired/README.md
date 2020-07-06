<h1>스프링 AUTOWIRE</h1>

<h3>@Autowire</h3>
- required: defalut true(못찾을시 어플리케이션 구동실패 false시 빈이 없어도 구동가능)

<h3>사용가능한 위치</h3>

- 생성자(constructor)
<pre>
<code>@Autowired
public BookService(BookRepository bookRepository) {
  this.bookRepository = bookRepository;
}</code>
</pre>

- 세터 (Setter)
<pre>
<code>@Autowired
public void setBookRepository(BookRepository bookRepository) {
             this.bookRepository = bookRepository;
 }</code>
</pre>

- 필드 (field)
<pre>
<code>@Autowired
BookRepository bookRepository;</code>
</pre>

<h3>같은 타입의 빈이 여러개 일 때 </h3>

- @Primary(어노테이션을 통해 주로 사용하려고 하는 빈을 선택)

- 해당타입 빈 모두 주입 받기

- @Qualifier (빈이름을 통해 주입받기)
