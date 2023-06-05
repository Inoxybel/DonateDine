# Descrição

---

# Arquitetura do projeto

---

## Endpoints
- Usuário
  - [Cadastrar](#cadastrar-usuário)
  - [Atualizar Cadastro](#atualizar-usuário)
  - [Login](#login-usuário)
- Lote
  - [Criar Novo](#criar-lote)
  - [Recuperar](#recuperar-lote)
  - [Recuperar Por Quantidade](#recuperar-lotes-por-quantidade)
  - [Recuperar Por Unidade](#recuperar-lotes-por-unidade-de-medida)
  - [Recuperar Por Fornecedor](#recuperar-lotes-por-fornecedor)
  - [Recuperar A Partir Da Data De Criação](#recuperar-lotes-a-partir-da-data-de-criação)
- Estoque
  - [Criar Novo](#criar-novo-estoque)
  - [Recuperar](#recuperar-estoque)
- Item
  - [Criar Novo](#criar-itens)
  - [Recuperar Todos do Estoque](#recuperar-todos-itens-do-estoque)
  - [Recuperar Um Item do Estoque](#recuperar-um-item-do-estoque)
-  Doação
    - [Criar Nova](#criar-uma-nova-doação)
    - [Recuperar](#recuperar-uma-doação)
- Status
    - [Estatísticas](#recuperar-estatísticas-do-sistema)
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
{
    "id": "0a0619c2-9cc6-4334-9822-88f50f49502f",
    "nome": "Postman",
    "email": "postman@postman.com",
    "senha": "$2a$10$6I1pPyKTnLMZ2KF330l87OfnwANuuS4UARoHu.UxSh55lUBqBIfy.",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/cadastrar"
        },
        "atualizar": {
            "href": "http://localhost:8080/api/usuario/0a0619c2-9cc6-4334-9822-88f50f49502f"
        },
        "logar": {
            "href": "http://localhost:8080/api/usuario/login"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuário cadastrado com sucesso |
| 400 | Erro na requisição |

---

## Atualizar Usuário
`PUT` /api/usuario/{id}

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | não | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | não | é o email do usuário, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| senha | string | não | é a senha do usuário, deve ter no mínimo 8 caracteres


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
{
    "id": "0a0619c2-9cc6-4334-9822-88f50f49502f",
    "nome": "Postman",
    "email": "postman@postman.com",
    "senha": "$2a$10$6I1pPyKTnLMZ2KF330l87OfnwANuuS4UARoHu.UxSh55lUBqBIfy.",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/cadastrar"
        },
        "atualizar": {
            "href": "http://localhost:8080/api/usuario/0a0619c2-9cc6-4334-9822-88f50f49502f"
        },
        "logar": {
            "href": "http://localhost:8080/api/usuario/login"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuário atualizado com sucesso
| 400 | Erro na requisição

---

## Login Usuário
`POST` /api/usuario/login

| Campo | Tipo | Obrigatório | Descrição |
|:-------:|:------:|:-------------:|:-------------:|
| email | string | sim | é o email do usuário |
| senha | string | sim | é a senha do usuário |

**Exemplo de corpo do request**
```json
{
    "email": "postman@postman.com",
    "senha": "Senha123"
}
```

**Exemplo de corpo de response**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwb3N0bWFuQHBvc3RtYW4uY29tIiwiZXhwIjoxNjg1OTc3MjIyLCJpc3MiOiJEcmVhbUNvbnRyb2wifQ.baJ8L9QOoe7_5Jlv0cIad0nMgZRmyZRbraJVLmLhgzo",
    "type": "JWT",
    "prefix": "Bearer",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/usuario/login"
        },
        "atualizar": {
            "href": "http://localhost:8080/api/usuario/1c00668b-ae9a-42ca-a5dc-784c384f4e77"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Login efetuado com sucesso |
| 400 | Erro na requisição |
| 401 | Falha na autenticação |

---

## Criar Lote
`POST` /api/lote

| Campo | Tipo | Obrigatório | Descrição |
|:-------:|:------:|:-------------:|:-------------:|
| quantidade | int | sim | a quantidade do lote |
| unidade_medida | string | sim | a unidade de medida do lote |
| descricao | string | sim | a descrição do lote |
| fornecedor | string | sim | o fornecedor do lote |

**Exemplo de corpo do request**
```json
{
    "quantidade": 10,
    "unidade_medida": "KILOGRAMA",
    "descricao": "frutas diversas",
    "fornecedor": "FIAP"
}
```

**Exemplo de corpo de response**
```json
{
    "id": "e1cbf04a-ef29-417e-8712-705ee18de95a",
    "quantidade": 10,
    "unidadeMedida": "KILOGRAMA",
    "descricao": "frutas diversas",
    "fornecedor": "FIAP",
    "dataCriacao": "2023-06-04T19:01:42.9981971",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/e1cbf04a-ef29-417e-8712-705ee18de95a"
        },
        "lotesPorQuantidade": {
            "href": "http://localhost:8080/api/lote/quantidade/10"
        },
        "lotesPorUnidadeMedida": {
            "href": "http://localhost:8080/api/lote/unidademedida/KILOGRAMA"
        },
        "lotesPorFornecedor": {
            "href": "http://localhost:8080/api/lote/fornecedor/FIAP"
        },
        "lotesPorDataCriacao": {
            "href": "http://localhost:8080/api/lote/datacriacao/2023-06-04T19%3A01%3A42.9981971"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuário atualizado com sucesso |
| 400 | Erro na requisição |

---

## Recuperar Lote
`GET` /api/lote/{id}

**Exemplo de corpo de response**
```json
{
    "id": "3f157605-b9be-4da2-93f5-62df54380bc4",
    "quantidade": 10,
    "unidadeMedida": "KILOGRAMA",
    "descricao": "frutas diversas",
    "fornecedor": "FIAP",
    "dataCriacao": "2023-06-04T19:07:23",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/3f157605-b9be-4da2-93f5-62df54380bc4"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuário atualizado com sucesso |
| 400 | Erro na requisição |
| 404 | Lote não encontrado |

---

## Recuperar Lotes Por Quantidade
`GET` /api/lote/quantidade/{quantidade}

**Exemplo de corpo de response**
```json
{
    "_embedded": {
        "loteResponseDTOList": [
            {
                "id": "3f157605-b9be-4da2-93f5-62df54380bc4",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:07:23",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/3f157605-b9be-4da2-93f5-62df54380bc4"
                    }
                }
            },
            {
                "id": "8bf2e0e1-e536-4208-8dd2-1601d3a67e6f",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:11:19",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/8bf2e0e1-e536-4208-8dd2-1601d3a67e6f"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/quantidade/10"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Lotes recuperados com sucesso |
| 400 | Erro na requisição |
| 404 | Lotes não encontrados |

---

## Recuperar Lotes Por Unidade de Medida
`GET` /api/lote/unidademedida/{unidademedida}

**Exemplo de corpo de response**
```json
{
    "_embedded": {
        "loteResponseDTOList": [
            {
                "id": "cdb2fb12-e108-4584-bf27-20c0d01325bb",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T15:48:15",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/cdb2fb12-e108-4584-bf27-20c0d01325bb"
                    }
                }
            },
            {
                "id": "e1cbf04a-ef29-417e-8712-705ee18de95a",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:01:43",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/e1cbf04a-ef29-417e-8712-705ee18de95a"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/unidademedida/KILOGRAMA"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Lotes recuperados com sucesso |
| 400 | Erro na requisição |
| 404 | Lotes não encontrados |

---

## Recuperar Lotes Por Fornecedor
`GET` /api/lote/fornecedor/{fornecedor}

**Exemplo de corpo de response**
```json
{
    "_embedded": {
        "loteResponseDTOList": [
            {
                "id": "8bf2e0e1-e536-4208-8dd2-1601d3a67e6f",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:11:19",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/8bf2e0e1-e536-4208-8dd2-1601d3a67e6f"
                    }
                }
            },
            {
                "id": "e1cbf04a-ef29-417e-8712-705ee18de95a",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:01:43",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/e1cbf04a-ef29-417e-8712-705ee18de95a"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/fornecedor/FIAP"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Lotes recuperados com sucesso |
| 400 | Erro na requisição |
| 404 | Lotes não encontrados |

---

## Recuperar Lotes A Partir da Data de Criação
`GET` /api/lote/datacriacao/{dataCriacao}

**Exemplo de corpo de response**
```json
{
    "_embedded": {
        "loteResponseDTOList": [
            {
                "id": "3f157605-b9be-4da2-93f5-62df54380bc4",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T19:07:23",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/3f157605-b9be-4da2-93f5-62df54380bc4"
                    }
                }
            },
            {
                "id": "cdb2fb12-e108-4584-bf27-20c0d01325bb",
                "quantidade": 10,
                "unidadeMedida": "KILOGRAMA",
                "descricao": "frutas diversas",
                "fornecedor": "FIAP",
                "dataCriacao": "2023-06-04T15:48:15",
                "_links": {
                    "item": {
                        "href": "http://localhost:8080/api/lote/cdb2fb12-e108-4584-bf27-20c0d01325bb"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/lote/datacriacao/2023-06-01T12%3A30%3A00"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Lotes recuperados com sucesso |
| 400 | Erro na requisição |
| 404 | Lotes não encontrados |

---

## Criar Novo Estoque
`POST` /api/estoque

**Exemplo de corpo do request**
```json
{
  "id_lote": "8bf2e0e1-e536-4208-8dd2-1601d3a67e6f"
}
```

**Exemplo de corpo de response**
```json
{
    "id": "7a2e4a10-dac3-4314-87a1-806015e364f2",
    "loteId": "8bf2e0e1-e536-4208-8dd2-1601d3a67e6f",
    "doacaoId": null,
    "dataCriacao": "2023-06-04T21:11:42.3809821"
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 201 | Estoque criado com sucesso |
| 400 | Erro na requisição |

---

## Recuperar Estoque
`GET` /api/estoque/{id}

**Exemplo de corpo de response**
```json
{
    "id": "7a2e4a10-dac3-4314-87a1-806015e364f2",
    "loteId": "8bf2e0e1-e536-4208-8dd2-1601d3a67e6f",
    "doacaoId": null,
    "dataCriacao": "2023-06-04T21:11:42"
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Estoque recuperado com sucesso |
| 400 | Erro na requisição |
| 404 | Estoque não encontrado |

---

## Criar Itens
`POST` /api/itens

**Exemplo de corpo do request**
```json
[
    {
        "nome": "banana",
        "path_imagem": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img1.png",
        "classificacao": "Boa"
    },
    {
        "nome": "banana",
        "path_imagem": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img2.png",
        "classificacao": "Ruim"
    }
]
```

**Exemplo de corpo de response**
```json
{
    "_embedded": {
        "itemResponseDTOList": [
            {
                "id": "42eb304b-6863-4029-9500-3b8703f81afb",
                "estoqueId": "7a2e4a10-dac3-4314-87a1-806015e364f2",
                "classificacaoId": "a300dd00-74d5-44a4-a9a5-773d5761f102",
                "nome": "banana",
				"classificacao": "Ruim",
                "imagemPath": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img1.png"
            },
            {
                "id": "3339dbc8-48f1-4d5e-8a25-7f01bfc8fe30",
                "estoqueId": "7a2e4a10-dac3-4314-87a1-806015e364f2",
                "classificacaoId": "a02e7dd6-f45b-4cc7-9caa-dc51751a5d23",
                "nome": "banana",
				"classificacao": "Ruim",
                "imagemPath": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img2.png"
            }
        ]
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 201 | Itens criados com sucesso |
| 400 | Erro na requisição |

---

## Recuperar Todos Itens do Estoque
`GET` /api/estoque/{estoqueId}/itens

**Exemplo de corpo de response**
```json
{
    "content": [
        {
            "id": "3339dbc8-48f1-4d5e-8a25-7f01bfc8fe30",
            "estoqueId": "7a2e4a10-dac3-4314-87a1-806015e364f2",
            "classificacaoId": "a02e7dd6-f45b-4cc7-9caa-dc51751a5d23",
            "nome": "banana",
			"classificacao": "Ruim",
            "imagemPath": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img2.png"
        },
        {
            "id": "42eb304b-6863-4029-9500-3b8703f81afb",
            "estoqueId": "7a2e4a10-dac3-4314-87a1-806015e364f2",
            "classificacaoId": "a300dd00-74d5-44a4-a9a5-773d5761f102",
            "nome": "banana",
			"classificacao": "Ruim",
            "imagemPath": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img1.png"
        }
    ],
    "number": 0,
    "totalElements": 2,
    "totalPages": 1,
    "first": true,
    "last": true
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Itens recuperados com sucesso |
| 400 | Erro na requisição |
| 404 | Itens não encontrados |

---

## Recuperar um Item do Estoque
`GET` /api/estoque/{estoqueId}/item/{itemId}

**Exemplo de corpo de response**
```json
{
    "id": "82ca6e88-68a4-40ab-9d4f-660ca68d8729",
    "estoqueId": "b7b701da-0ce6-488d-871f-25706c78d40a",
    "classificacaoId": "63e0e61b-824a-4db0-be9c-dbf2b6b4818e",
    "nome": "banana",
    "classificacao": "Ruim",
    "imagemPath": "C:\\Users\\thdev\\Documentos\\Fiap\\GLOBAL SOLUTION 1\\AI\\Classificacao\\Img2.png",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/estoque/b7b701da-0ce6-488d-871f-25706c78d40a/item/82ca6e88-68a4-40ab-9d4f-660ca68d8729"
        },
        "Relacao": {
            "href": "http://localhost:8080/api/estoque/b7b701da-0ce6-488d-871f-25706c78d40a/itens"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Item recuperado com sucesso |
| 400 | Erro na requisição |
| 404 | Item não encontrado |

---

## Criar uma Nova Doação
`POST` /api/doacao

**Exemplo de corpo do request**
```json
{
    "descricao":"Doação feita para a igreja Tal localizada no endereco XPTO e recebido por Fulano de Tal",
    "cnpj_destinatario":"22.111.111.0001/11"
}
```

**Exemplo de corpo de response**
```json
{
    "id": "41315f36-9799-4baa-9fee-63da0fdcf81a",
    "estoqueId": "cfdce258-83af-440b-8986-f38476a1366f",
    "descricao": "Doação feita para a igreja Tal localizada no endereco XPTO e recebido por Fulano de Tal",
    "cnpjDestinatario": "22.111.111.0001/11",
    "dataCriacao": "2023-06-05T08:51:54.1274266",
    "_links": {
        "estoque": {
            "href": "http://localhost:8080/api/estoque/cfdce258-83af-440b-8986-f38476a1366f"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 201 | Doação criada com sucesso |
| 400 | Erro na requisição |

---

## Recuperar uma Doação
`GET` /api/doacao/{id}

**Exemplo de corpo de response**
```json
response
{
    "id": "e7488153-7948-4679-ae73-9ac5e7beb345",
    "estoqueId": "089ed730-e1fb-4407-8b01-ab0d6bf96845",
    "descricao": "Doação feita para a igreja Tal localizada no endereco XPTO e recebido por Fulano de Tal",
    "cnpjDestinatario": "11.111.111.0001/11",
    "dataCriacao": "2023-06-05T08:43:03",
    "_links": {
        "estoque": {
            "href": "http://localhost:8080/api/estoque/089ed730-e1fb-4407-8b01-ab0d6bf96845"
        }
    }
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Doação recuperada com sucesso |
| 400 | Erro na requisição |
| 404 | Doação não encontrada |

---

## Recuperar Estatísticas do Sistema
`GET` /api/stats

Retorna o total de lotes, estoques e doações no sistema.

**Exemplo de corpo de response**
```json
{
    "totalLotes": 100,
    "totalEstoques": 80,
    "totalDoacoes": 50
}
```

**Códigos de Resposta**
| Código | Descrição |
|:-------:|:-----------:|
| 200 | Estatísticas recuperadas com sucesso |
| 400 | Erro na requisição |