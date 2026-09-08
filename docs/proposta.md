# Proposta do produto — ContasDeViagem

**Disciplina:** DIM0524 — Desenvolvimento de Sistemas para Dispositivos Móveis

**Semestre:** 2026.2

**Repositório:** https://github.com/joaopbmello/ContasDeViagem

---

## 1. Visão do produto

```
Para vendedores externos que viajam a trabalho e precisam prestar contas das despesas à empresa
Que anotam cada gasto num caderno durante a viagem e, na volta, redigitam tudo num modelo de Word somando os valores na calculadora
O ContasDeViagem é um aplicativo móvel de registro de despesas de viagem
Que permite lançar cada despesa no momento em que ela acontece, com foto do comprovante, mesmo sem sinal na estrada
Diferente do caderno somado à mão e do formulário preenchido depois
Nosso produto totaliza sozinho e gera a prestação de contas pronta, no mesmo formato que a empresa já usa hoje
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

**Hipótese de valor:** acreditamos que vendedores externos vão lançar cada despesa durante a viagem, em vez de acumular anotações num caderno, porque o aplicativo soma sozinho e entrega o documento no formato exigido, eliminando a redigitação na volta.

---

## 4. Plataforma-alvo

**Escolha: Android.**

O produto é usado em campo, durante deslocamentos rodoviários pelo interior do nordeste, no momento em que a despesa acontece. Seja no posto, no restaurante, no hotel. O aparelho é o celular que o vendedor já carrega, e nenhuma etapa do fluxo acontece em um computador.

O vendedor com quem o produto será validado usa Android. Além disso, o Android responde por mais de 80% do mercado de sistemas móveis no Brasil, com concentração ainda maior nas faixas de aparelho mais acessíveis, típicas do público de vendedores externos.

**Alternativa descartada: iOS.**

Minoritário no público-alvo brasileiro e ausente entre os usuários com quem o produto será validado. Escolher iOS otimizaria para um perfil de usuário que não é o do produto.
 
---

## 5. Estratégia de backend

**Escolha: Supabase.**

Hoje o registro das despesas existe num único caderno de papel; no aplicativo, existiria num único aparelho. Perder, quebrar ou trocar o celular durante uma viagem significaria perder a prestação de contas inteira — ou seja, dinheiro que o vendedor adiantou e não conseguiria comprovar. A conta existe para que o histórico não dependa do aparelho.

Além disso, uma despeza pode ser inserida sem sinal e subida depois, o que exige um servidor capaz de receber escritas fora de ordem e resolver conflitos de forma previsível. O domínio é relacional e pequeno: uma viagem tem muitas despesas, e as consultas que importam são por viagem e por período, com totalização. O PostgreSQL do Supabase modela isso diretamente, com integridade referencial e soma no banco, e resolve autenticação e armazenamento de arquivos no mesmo serviço.

**Alternativas descartadas.**

**Firebase:** Atende autenticação e armazenamento de arquivos, mas o Firestore é orientado a documentos e restringe as consultas ao seu próprio modelo. Um domínio que é relacional por natureza exigiria desnormalizar os dados e manter totais duplicados à mão, aumentando a chance de o total exibido divergir da soma das despesas.

**API própria de Web II:** Exige cursar DIM0547, o que não é o caso neste semestre.

**Local com APIs públicas.** Removeria a sincronização e a conta de usuário, que são esseiciais em caso de perda do aparelho. Além disso, não existe serviço externo de prestação de contas de viagem a consumir.