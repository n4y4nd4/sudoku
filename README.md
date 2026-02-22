# Sudoku - Exercício de Programação em Java Swing

![Java](https://img.shields.io/badge/Language-Java-007396?logo=java)

## Visão geral

Este repositório contém a solução de um **exercício acadêmico** de programação que implementa um jogo de **Sudoku** utilizando **Java Swing** e o padrão arquitetural **MVC (Model–View–Controller)**.
O principal objetivo foi praticar:

* Criação de interfaces gráficas interativas.
* Separação de responsabilidades entre camadas (Model, View e Controller).
* Validação de regras de negócio.
* Manipulação de eventos e componentes gráficos em Java.

A aplicação permite que o usuário interaja com o tabuleiro de Sudoku por meio de uma interface gráfica moderna, sem necessidade de uso do terminal.

---

## Exigências do exercício

O sistema apresenta **um menu interativo** com as seguintes funcionalidades:

1. **Iniciar um novo jogo**
   Exibe o tabuleiro inicial, preenchendo apenas os números fixos.
   Os números iniciais e suas posições são informados por meio dos argumentos do método `main`.

2. **Colocar um novo número**
   O usuário seleciona uma célula do tabuleiro e informa o número desejado.
   O sistema impede a inserção em posições já preenchidas, sejam fixas ou inseridas pelo jogador.

3. **Remover um número**
   Ativa um modo de remoção. Ao clicar em uma célula:

    * Se o número for inserido pelo usuário, ele será removido.
    * Se for um número fixo, o sistema exibe uma mensagem informando que não é possível removê-lo.

4. **Verificar jogo**
   Permite visualizar a situação atual do tabuleiro.

5. **Verificar status do jogo**
   Informa o status atual do jogo:

    * Não iniciado
    * Incompleto
    * Completo

   Também informa se há conflitos (números repetidos em linha, coluna ou região 3×3).
   O status *não iniciado* nunca possui erros.

6. **Limpar**
   Remove todos os números inseridos pelo jogador, mantendo apenas os números fixos.

7. **Finalizar o jogo**
   Verifica se o tabuleiro está completo e válido.
   Caso esteja correto, o jogo é encerrado. Caso contrário, o sistema solicita que o usuário complete corretamente o tabuleiro.

---

## Funcionalidades implementadas

* Interface gráfica com Java Swing.
* Destaque visual para números fixos.
* Bordas reforçadas para melhor visualização das regiões 3×3.
* Validação de jogadas em tempo real.
* Detecção de conflitos (linhas, colunas e regiões).
* Modo de inserção e modo de remoção.
* Atualização automática do status do jogo.
* Estrutura organizada seguindo o padrão MVC:

    * `model`
    * `service`
    * `controller`
    * `view`

---

## Estrutura do projeto

```
src/
 ├── model
 ├── service
 ├── controller
 └── view
```

Essa separação garante melhor organização, manutenção e escalabilidade do código.

---

## Como executar

### Pré-requisitos

* JDK 11 ou superior.
* IDE recomendada: IntelliJ IDEA.

---

### Executando pela IDE

Na configuração de execução, informe os argumentos do programa no campo **Program arguments** no seguinte formato:

```
linha,coluna,valor
```

Cada posição deve ser separada por espaço.

Exemplo:

```
0,0,5 0,4,7 0,8,3 4,0,6 4,4,1 4,8,9 8,0,2 8,4,8 8,8,4
```

Cada argumento representa:

* `linha` → posição vertical (0 a 8)
* `coluna` → posição horizontal (0 a 8)
* `valor` → número fixo inicial

---

### Compilação usando `javac`

```bash
javac -d out $(find src -name "*.java")
```

---

### Execução pelo terminal

```bash
java -cp out Main 0,0,5 0,4,7 0,8,3 4,0,6 4,4,1 4,8,9 8,0,2 8,4,8 8,8,4
```

---

## Possíveis melhorias futuras

* Geração automática de puzzles.
* Níveis de dificuldade.
* Destaque visual de erros no tabuleiro.
* Tema escuro (Dark Mode).
* Animações e melhor experiência do usuário.
* Salvamento e carregamento de partidas.

---

## Contribuição

Contribuições são bem-vindas!
Sinta-se à vontade para:

* Corrigir bugs.
* Melhorar a validação.
* Adicionar novas funcionalidades.
* Refatorar a arquitetura.

Abra uma *issue* antes de propor grandes alterações.

---

## Licença

Projeto desenvolvido para fins acadêmicos.
