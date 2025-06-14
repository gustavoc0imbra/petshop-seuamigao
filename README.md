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
- MySQLs

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
- Após executado os serviços estarão disponíveis nas portas abaixo:

## Portas utilizadas pelos serviços:
| API | Porta |
| --- | ----- |
| petshopfrontend | 80 |
| productscatalogapi | 8080 |
| productscatalog-mysql | 3306 |
| usersapi (Autenticação e Usuários) | 8081 |
| users-mysql | 3307 |
| ordersapi | 8082 |
| orders-mysql | 3308 |
| paymentsapi | 8083 |
| payments-mysql | 3309 |

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


## Diagrama arquitetura:
![arquitetura_prototipo](https://github.com/user-attachments/assets/4f5ce2dd-f03c-4f39-addd-6a254992552b)
