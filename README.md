# Petshop- Seu Amigão 🐶🐱

O Petshop - Seu Amigão é um e-commerce para vender produtos relacionados a pets.

## Funcionalidades
- O sistema possibilita cadastrar e efetuar login de usuários
- Gerenciar usuários
- Cadastrar/atualizar/remover/buscar produtos (Rações, roupas etc...)
- Gerenciamento de vendas
- Gerenciamento do pagamentos das vendas
- 75 % dos endpoints são cobertos pela camada de segurança do JWT, por todos comunicarem com o serviço de autenticação/usuários

## Tecnologias
- Java (JDK 17)
- SpringBoot
- React
- JS
- MySQLs

## Requisitos para rodar:
- Docker instalado
- Docker compose instalado
- JDK 17
- Node e NPM (caso opte rodar o frontend sem ser em um container

## Como instalar e rodar (com docker):
- Clonar este repositório deseja
```
git clone 
```
- Acessar a pasta do projeto pelo terminal e executar o comando docker-compose para subir todas as aplicações de uma vez:
```
cd petshop-seuamigao
docker-compose up -d
```
- Após executado todos os serviços (incluindo frontend) estarão disponíveis nas portas abaixo:

> [!NOTE]
> O projeto frontend também é possivel ser executado sem estar no docker caso deseje  
> Para fazer isto basta acessar a pasta do projeto `petshop-front` via terminal: `cd petshop-front`  
> E rodar o comando (assumindo que o node e npm já tenha previamente instalado): `npm install && npm run dev`

## Portas utilizadas pelos serviços:
| API | Porta |
| --- | ----- |
| petshopfrontend | [80](http://localhost:80) |
| productscatalogapi | [8080](http://localhost:8080/api/docs) |
| productscatalog-mysql | 3306 |
| usersapi (Autenticação e Usuários) | [8081](http://localhost:8081/api/docs) |
| users-mysql | 3307 |
| ordersapi | [8082](http://localhost:8082/api/docs) |
| orders-mysql | 3308 |
| paymentsapi | [8083](http://localhost:8083/api/docs) |
| payments-mysql | 3309 |

> [!NOTE]
> `nomedoservico-mysql` representa o serviço de banco de dados MySQL relacionado ao serviço

> [!NOTE]
> Ao executar vem um usuário **padrão**  
> Email: `admin@email.com`  
> Senha: `admin123`

## Funcionalidade cada serviço:
### petshopfrontend:
- Prover um frontend para estar acessando as apis
- Tela Inicial exibindo os produtos cadastrados:
**OBS: É necessário salvar alguns produtos via POST na productscatalogapi**
![image](https://github.com/user-attachments/assets/de4a5c0d-b5e0-4b76-95fc-62f67d4ba382)
- Visualizar um produto:
![image](https://github.com/user-attachments/assets/1e52c9d3-69d1-4bfe-998c-a49664109fae)
- Login (ao fazer login é redirecionado automaticamente para tela inicial):
![image](https://github.com/user-attachments/assets/fe211d1b-be44-42a7-b5c8-2cc4d49d0e39)
- Registrar-se (ao cadastrar-se é feito login automaticamente):
![image](https://github.com/user-attachments/assets/ced41883-4ad8-46b5-9db4-7555a5cfcc4c)

> [!NOTE]
> **(usersapi, productscatologapi e ordersapi precisam de autenticação via JWT em todos endpoints**
> Exceto em alguns casos que será indicado qual não é preciso.

### usersapi:
- Prover uma API para cadastrar, buscar, excluir, atualizar os usuários do petshop
- Prove também 2 enpoints para login e autenticação
- **SOMENTE** o endpoint de POST para se registrar e login não necessita ser autenticado via JWT
- Documentação com Swagger: ```http://localhost:8081/api/docs```

### productscatalogapi:
- Prover uma API para cadastrar, buscar, excluir, atualizar os produtos do petshop
- **SOMENTE** o endpoint de buscar todos e buscar por id não necessita ser autenticado via JWT
- Documentação com Swagger: ```http://localhost:8080/api/docs```

### ordersapi:
- Prover uma API para cadastrar, buscar, excluir, atualizar os pedidos do petshop
- Documentação com Swagger: ```http://localhost:8082/api/docs```

### paymentsapi:
- Prover uma API para cadastrar, buscar, excluir, atualizar os pagamentos dos pedidos do petshop
- Não possui autenticação com JWT
- Documentação com Swagger: ```http://localhost:8083/api/docs```

## Diagrama arquitetura:
![arquitetura_prototipo](https://github.com/user-attachments/assets/4f5ce2dd-f03c-4f39-addd-6a254992552b)
