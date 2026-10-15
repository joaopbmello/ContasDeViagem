# ContasDeViagem

Aplicativo móvel para registro de despesas de viagem e geração da prestação de contas. Projeto da disciplina DIM0524 — Desenvolvimento de Sistemas para Dispositivos Móveis, UFRN, 2026.2.

## Equipe

| Nome | Matrícula | Papel |
|---|---|---|
| João Mello | 20250050643 | Desenvolvimento integral (equipe de um integrante) |

**Coorte de apresentação:** B

## Vídeo da Sprint 0

https://drive.google.com/file/d/1n7WC4DXrIGBSrXHMZNYD5Gspc28wOtb7/view

## Ambiente

Desenvolvido em Windows. O `kdoctor` é exclusivo de macOS; a verificação de ambiente foi feita pelas checagens do plugin de Kotlin Multiplatform no Android Studio.

## Alvos

Android (prioritário) e desktop. Os alvos iOS estão declarados no projeto, mas não são compiláveis no ambiente de desenvolvimento usado.

## Sprint 1

### Deep link

Com o app instalado no emulador, o comando abaixo abre direto a viagem 1:

    adb shell am start -a android.intent.action.VIEW -d "contasdeviagem://viagem/1"

Um id inexistente, como 99, mostra a tela de viagem não encontrada.

### Acessibilidade

Testado com o TalkBack no emulador. O que foi verificado:

- Títulos de tela anunciados como cabeçalho
- Cada cartão de viagem e de despesa lido como um bloco só
- Botões com texto visível (Voltar, Nova viagem, Lançar despesa, Salvar)
- Mensagens de erro dos formulários anunciadas junto do campo
- Opções de comprovante lidas como botões de opção
- Alvos de toque de pelo menos 48 dp
- Cores só pelos papéis do tema, sem cor fixa