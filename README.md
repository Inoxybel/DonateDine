# Descrição

---

# Arquitetura do projeto

---

## Endpoints
- Usuário
  - [Cadastrar](#cadastrar-usuario)
  - [Atualizar Cadastro](#atualizar-usuario)
  - [Login](#login-usuario)
- Lote
  - [Criar Novo](#criar-lote)
  - [Recuperar](#recuperar-lote)
  - [Recuperar Por Quantidade](#recuperar-lotes-por-quantidade)
  - [Recuperar Por Unidade](#recuperar-lotes-por-unidade)
  - [Recuperar Por Fornecedor](#recuperar-lotes-por-fornecedor)
  - [Recuperar A Partir Da Data De Criação](#recuperar-lotes-data-criacao)
- Estoque
  - [Criar Novo](#criar-estoque)
  - [Recuperar](#recuperar-estoque)
- Item
  - [Criar Novo](#criar-item)
  - [Recuperar Todos do Estoque](#recuperar-todos-itens-estoque)
  - [Recuperar Um Item do Estoque](#recuperar-item-estoque)

---

## Cadastrar Usuário
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | sim | é o email do usuário, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| senha | string | sim | é a senha do usuário, deve ter no mínimo 8 caracteres


**Exemplo de corpo do request**
```json
{
	"nome": "Postman",
	"email": "postman@postman.com",
	"senha": "Senha123"
}
```

**Exemplo de corpo de response**
```json

```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuário atualizado com sucesso
| 201 | Usuário cadastrado com sucesso
| 400 | Erro na requisição

---

