# Recurso 2015 - Grupo I

## 1

As operações de lock e unlock são usadas para evitar condições de corrida, onde múltiplas threads tentam acessar e modificar o mesmo recurso simultaneamente, potencialmente levando a resultados incorretos ou inconsistentes. A operação de lock é usada para garantir a exclusividade de acesso a um recurso compartilhado. A operação de unlock (ou desbloqueio) é usada para liberar o lock, permitindo que outras threads ou processos possam adquirir o lock e acessar o recurso compartilhado.

## 2

???

## 3

Em uma única máquina, os processos ou threads compartilham o mesmo espaço de memória e recursos de hardware, o que permite o uso de várias técnicas e mecanismos de sincronização de forma direta e eficiente. Em sistemas distribuídos, onde processos estão localizados em diferentes máquinas conectadas por uma rede, assegurar a exclusão mútua torna-se mais complexo devido à latência da rede, falhas de comunicação, e a falta de um relógio global.