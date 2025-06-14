# petshop-seuamigao

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

## Portas utilizadas pelos serviços:
| API | Porta |
| usersapi (Autenticação e Usuários) | 8081 |
| users-mysql | 3306 |
| productscatalogapi | 8080 |
| productscatalog-mysql | 8080 |
| ordersapi | 8082 |
| ordersapi | 8082 |
| paymentsapi | 8083 |
| paymentsapi | 8083 |