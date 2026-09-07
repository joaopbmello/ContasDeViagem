# Proposta do produto — ContasDeViagem

**Disciplina:** DIM0524 — Desenvolvimento de Sistemas para Dispositivos Móveis
**Semestre:** 2026.2
**Repositório:** https://github.com/joaopbmello/ContasDeViagem

---

## 1. Visão do produto

```
Para vendedores externos que viajam a trabalho e precisam prestar contas
das despesas à empresa
Que anotam cada gasto num caderno durante a viagem e, na volta,
redigitam tudo num modelo de Word somando os valores na calculadora
O ContasDeViagem é um aplicativo móvel de registro de despesas de viagem
Que permite lançar cada despesa no momento em que ela acontece, com foto
do comprovante, mesmo sem sinal na estrada
Diferente do caderno somado à mão e do formulário preenchido depois
Nosso produto totaliza sozinho e gera a prestação de contas pronta, no
mesmo formato que a empresa já usa hoje
```

---

## 2. Definição do MVP

| No MVP | Fora do MVP |
| --- | --- |
| Cadastro de viagem (período, saída, destino, valor adiantado) | Perfil validador e fluxo de aprovação pela empresa |
| Lançamento de despesa com formulário validado | Preenchimento automático da cidade por GPS |
| Foto do comprovante anexada à despesa | Leitura automática de valores do comprovante (OCR) |
| Total e saldo calculados automaticamente | Categorias configuráveis e múltiplas moedas |
| Lista das despesas da viagem, com indicação do que ainda não sincronizou | Relatórios, gráficos e histórico analítico |
| Lançamento sem sinal, com fila de envio e sincronização ao reconectar | Painel web e acesso por gestores da empresa |
| Conta de usuário, para não perder o histórico ao trocar de aparelho | Edição do modelo do documento e suporte a outros formatos |
| Geração e compartilhamento da prestação de contas no formato usado pela empresa | Integração com o sistema financeiro da empresa |

**Hipótese de valor:** acreditamos que vendedores externos vão lançar cada
despesa durante a viagem, em vez de acumular anotações num caderno, porque o
aplicativo soma sozinho e entrega o documento no formato exigido, eliminando a
redigitação na volta.