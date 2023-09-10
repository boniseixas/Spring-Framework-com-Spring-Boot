# Spring Framework - SpringBoot

O Spring Framework baseia-se no padrão de injeção de dependências enquanto que o spring Boot foca na configuração automática.

## Technical skills

<div style="text-align: center">
    <img src="./images/java.png" width="10%" />
    <img src="./images/springboot.png" width="10%" />
    <img src="./images/maven-logo.png" width="20%" />
    <img src="./images/rest.png" width="20%" />
</div>

## O que é Spring Boot?
O Spring Boot é um framework Java open source que tem por objetivo facilitar o processo de construção de aplicações Java.
Trazendo mais agilidade para o desenvolvimento, uma vez que devs conseguem reduzir o tempo gasto com as configurações iniciais.
Com o Spring Boot conseguimos abstrair e facilitar a configuração de, por exemplo:
+ Servidores;
+ Gerenciamento de dependências;
+ Configurações de bibliotecas;
+ Métricas & health checks;
+ E muito mais!!

<div style="text-align: center">
    <img src="images/springboot-build.webp" />
</div>

## Características
+ Crie aplicativos Spring independentes;
+ Incorpore Tomcat, Jetty ou Undertow diretamente (sem necessidade de implantar arquivos WAR);
+ Forneça dependências 'iniciais' opinativas para simplificar sua configuração de compilação;
+ Configure automaticamente bibliotecas Spring e de terceiros sempre que possível;
+ Forneça recursos prontos para produção, como métricas, verificações de integridade e configuração externalizada;
+ Absolutamente nenhuma geração de código e nenhum requisito para configuração XML.

Dado que a maior parte das configurações necessárias para o início de um projeto são sempre as mesmas,
por que não iniciar um projeto com todas estas configurações já definidas?

<div style="text-align: center">
    <a href="https://glysns.gitbook.io/spring-framework/core">
        <img src="images/spring-versus-springboot.gif" />
    </a>
</div>

## Spring Boot Starters
Os starters são dependências que agrupam outras dependências com um propósito em comum.
Dessa forma, somente uma configuração é realizada no gerenciador de dependências.</br>
Por exemplo, o spring-boot-starter-test, é um starter que permite a construção de teste.
Ele contém a maioria dos elementos necessários para os testes de uma aplicação.</br>
Existem vários tipos diferentes de testes que podemos escrever para ajudar a testar e
automatizar a integridade de um aplicativo. Com o Spring Boot, precisamos apenas adicionar a
dependência (starter) spring-boot-starter-test para testá-la.</br>
Ao realizar a configuração no arquivo pom.xml da aplicação, se define somente o starter:
~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
~~~
Ele extrai todas as dependências relacionadas a testes. Depois de adicioná-lo, podemos construir
um teste de unidade simples. Na dependência acima, temos o escopo do teste <scope>test</scope>.
Isso significa que quando o aplicativo é agrupado e empacotado para implantação, qualquer dependência
declarada com os escopos de teste será ignorada. As dependências do escopo de teste
estão disponíveis apenas durante a execução nos modos de desenvolvimento e de teste do Maven.

### Benefícios do uso do Starter
+ Coesão
+ Versões compatíveis
+ Otmização do tempo
+ Configuração simples
+ Foco no negócio

### Alguns Starters disponíveis
#### spring-boot-starter-*
+ **data-jpa:** Integração ao banco de dados via JPA - Hibernate.
+ **data-mongodb:** Interação com banco de dados MongoDB.
+ **web:** Inclusão do container Tomcat para aplicações REST.
+ **web-services:** Webservices baseados na arquitetura SOAP.
+ **batch:** Implementação de JOBs de processos.
+ **test:** Disponibilização de recursos para testes unitários como JUnit
+ **openfeign:** Client HTTP baseado em interfaces
+ **actuator:** Gerenciamento de monitoramento da aplicação.

## Beans versus Components
**@Bean:** é usada para indicar que um método de uma classe de configuração retorna um objeto
que deve ser gerenciado pelo contêiner Spring. O objeto retornado por esse método pode ser qualquer
objeto Java que o desenvolvedor deseja que o Spring gerencie. Por exemplo, um objeto de conexão de banco de dados,
um serviço REST ou um cliente HTTP. Quando o método com a anotação @Bean é chamado, o Spring cria
um objeto do tipo especificado e o adiciona ao contexto do aplicativo. Esse objeto pode ser posteriormente
injetado em outras classes que precisam dele através de seus construtores ou usando a anotação @Autowired.<br/>
**Exemplo:**
```java
    @Bean
    public Gson gson() {
        return new Gson();
    }
```
**@Component:** Tudo no Spring são componentes, logo quando iniciamos a aplicação será criado um objeto baseado
na classe StartApplication.java. Anotação a nível de classe para indicar que essa classe deverá ser exposta como
um bean e ela poderá ser injetada nos consumidores que vão precisar dela.<br/>
**Exemplo**
~~~java
@Component
public class ConversorJson {
    @Autowired
    private Gson gson;
    public ViaCepResponse converter(String json) {
        ViaCepResponse response = gson.fromJson(json,ViaCepResponse.class);
        return response;
    }
}
~~~

## Scopes Singleton or Prototype
### Bean scopes
Quando criamos uma definição de **bean**, o que você está realmente criando é uma **receita**
para criar instâncias reais da classe definida pelo **@Bean**. A ideia de que uma definição de bean
é uma receita é importante porque significa que, assim como uma classe, podemos potencialmente ter
muitas instâncias de objetos criadas a partir de uma única receita.<br/>
Pode controlar não apenas as diversas dependências e valores de configuração que serão conectados
a um objeto criado a partir de uma definição de bean específica, mas também o escopo dos objetos
criados a partir de uma definição de bean específica. Essa abordagem é muito poderosa e oferece
flexibilidade para escolher o escopo dos objetos criados por meio da configuração, em vez de ter que
'incorporar' o escopo de um objeto no nível da classe Java.

#### The prototype scope
Scopes a single bean definition to any number of object instances.
The non-singleton, prototype scope of bean deployment results in the creation of a new bean instance
every time a request for that specific bean is made (that is, it is injected into another bean or it is
requested via a programmatic getBean() method call on the container). As a rule of thumb, you should use
the prototype scope for all beans that are stateful, while the singleton scope should be used for stateless beans.<br/>

~~~java
@Configuration
public class Beans {
    @Bean
    @Scope("prototype")
    public Remetente remetente() {
        System.out.println("Criando um objeto remetente");
        Remetente remetente = new Remetente();
        remetente.setEmail("seso@seso.com.br");
        remetente.setNome("Boni SeSo");
        return remetente;
    }
}
~~~

#### The singleton scope
Scopes a single bean definition to a single object instance per Spring IoC container.<br/>
When a bean is a singleton, only one shared instance of the bean will be managed, and all requests
for beans with an id or ids matching that bean definition will result in that one specific bean
instance being returned by the Spring container.<br/>
To put it another way, when you define a bean definition and it is scoped as a singleton, then the
Spring IoC container will create exactly one instance of the object defined by that bean definition.
This single instance will be stored in a cache of such singleton beans, and all subsequent requests
and references for that named bean will result in the cached object being returned.

## Properties Value
A anotação Spring @Value é usada para atribuir valores padrão a variáveis e argumentos de método.
Podemos ler variáveis de ambiente Spring, bem como variáveis de sistema usando a anotação @Value.
A anotação Spring @Value também suporta SpEL.<br/>
Podemos atribuir um valor padrão a uma propriedade de classe usando a anotação @Value.
~~~java
@Value("Default PropertiesValue")
private String defaultName;
~~~
Podemos atribuir um valor padrão que será atribuído se a chave estiver faltando nas propriedades do ambiente Spring.
~~~java
    @Value("${nome:Seso}")
    private String nome;
~~~

## Configuration Properties
