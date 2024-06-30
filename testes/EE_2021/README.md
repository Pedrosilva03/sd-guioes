# Época Especial 2021 - Grupo I

## Escolha múltipla 

1. a), b) e c)

2. a) e d)

3. c)

4. a) e b)

## Desenvolvimento

O principal desafio na implementação de middleware de invocação remota que suporte clientes com vários threads e invocações concorrentes ao mesmo servidor é garantir a correta sincronização e gestão de recursos para evitar problemas como condições de corrida, deadlocks e contenção de recursos. Uma solução seria usar thread pools para limitar a criação e fecho de threads. Desta forma seria mais fácil controlar o número de threads e o número de invocações concorrentes. No cliente, isto permite que múltiplas invocações remotas sejam feitas em paralelo sem criar um número excessivo de threads. No servidor, uma thread pool pode processar vários pedidos simultaneamente, limitando o número máximo de threads ativas e, assim, evitando sobrecarga.