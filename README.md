
# 📇 Spring Contact SpreadSheet

Este é um projeto Java baseado no Spring MVC que fornece uma aplicação web para **gerenciamento de contatos** com suporte a **operações CRUD**, **persistência em banco de dados MySQL**, **renderização com Thymeleaf** e **configuração via `web.xml`**.

---

## 🚀 Tecnologias e Frameworks

- Java 17+
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL
- Maven

---

## 🗂️ Estrutura do Projeto

```
src/
├─ config/               # Configurações JPA e MVC
├─ controller/           # Camada de controladores REST e Thymeleaf
├─ model/                # Entidade Contact
├─ repository/           # Repositórios JPA e adaptadores
│  ├─ implementations/   # Implementação ContactJpaAdapter
│  └─ interfaces/        # Interfaces Gateway e JpaRepository
│
├─ service/              # Lógica de negócio
└─ resources/
   └─ application.properties
webapp/
└─ WEB-INF/
   ├─ views/             # Páginas HTML Thymeleaf
   └─ web.xml            # Configuração do servlet via XML
```

---

## ⚙️ Configuração

### `application.properties`

```properties
# Conexão com banco MySQL
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/ContactSpreadsheet?useSSL=false
db.username=root
db.password=root

# Hibernate
hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update
```

### `web.xml`

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
    </init-param>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.contactspreadsheet.config.WebConfig</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

---

## 🧩 Principais Componentes

- **`Contact.java`**: Entidade representando um contato com campos: `id`, `name`, `email`, `birthDate`.
- **`ContactController.java`**: Controlador com endpoints para listar, inserir, atualizar e deletar contatos.
- **`ContactService.java`**: Camada de lógica de negócio.
- **`ContactGateway.java`**: Interface de abstração para persistência.
- **`ContactJpaAdapter.java`**: Implementação do Gateway usando JPA.
- **`ContactJpaRepository.java`**: Interface que estende `JpaRepository`.
- **`WebConfig.java`**: Configuração Spring MVC e registrador de `LocalDateEditor`.
- **`JpaConfig.java`**: Configuração do JPA com `DataSource` e `TransactionManager`.
- **`LocalDateEditor.java`**: Conversor para entrada de datas no formato `dd/MM/yyyy`.

---

## 🌐 Interface Web (Thymeleaf)

### Páginas HTML

- `home.html`: Exibe tabela com todos os contatos e opções de ação (update/delete).
- `contact_insert.html`: Formulário de inserção de novo contato.
- `contact_update.html`: Formulário para edição de um contato existente.

### Campos dos formulários

- **Name**
- **Email**
- **Birth Date** (formato: `dd/MM/yyyy`)

---

## 📬 Endpoints

| Método | URL                         | Ação                          |
|--------|-----------------------------|-------------------------------|
| GET    | `/contacts`                 | Lista todos os contatos       |
| GET    | `/contacts/insert`          | Exibe formulário de inserção  |
| POST   | `/contacts/insert`          | Salva novo contato            |
| GET    | `/contacts/update?id={id}`  | Exibe formulário de update    |
| POST   | `/contacts/update`          | Atualiza contato existente    |
| GET    | `/contacts/delete?id={id}`  | Remove contato por ID         |

---

## ▶️ Como Executar Localmente

### Pré-requisitos

- JDK 17+
- Maven
- MySQL

### Passos

1. Criar o banco de dados:

```sql
CREATE DATABASE ContactSpreadsheet;
```

2. Clonar o repositório:

```bash
git clone https://github.com/seu-usuario/contactspreadsheet.git
cd contactspreadsheet
```

3. Compilar e rodar a aplicação:

```bash
mvn clean install
mvn tomcat7:run
```

4. Acesse em: `http://localhost:8080/`
