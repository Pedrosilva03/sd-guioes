# Recurso 2024 - Grupo I

## 1

O algoritmo de Peterson é uma técnica clássica para assegurar a exclusão mútua entre dois processos ou threads. No entanto, ele não é diretamente aplicável a mais de dois threads sem modificações significativas. O algoritmo de Peterson para dois threads utiliza duas variáveis principais:

- flag[i]: Indica se o thread i deseja entrar na região crítica

- turn: Indica de quem é a vez de tentar entrar na região crítica

Tomemos com exemplo o algoritmo de peterson a funcionar com 3 threads. Cada um destes threads quer entrar na secção critica. Quando cada um destes threads entra na secção critica flag[i] == 1 (i<3), logo cada thread vai ficar à espera que os outros mudem a sua flag. Estamos numa situação de deadlock, ou seja, o programa bloqueia.

## 2

A migração de código consiste em passar o processamento de certas tarefas do servidor para o cliente. Isto terá impacto na carga do servidor. Visto que parte do processamento passa a ser feito no cliente, o servidor irá ter menos tarefas para realizar. A migração de código pode tornar o sistema mais escalável. Esta redução da carga do servidor pode permitir que exista um número maior de utilizadores no sistema, aumentando assim a sua escalabilidade. O facto de parte do processamento ser passado para o cliente resulta também na redução da latência experênciada pelo mesmo.

## 3

Relógios lógicos, como os vetoriais ou de Lamport, fornecem uma maneira eficiente de coordenar e ordenar eventos em sistemas distribuídos. Estes ajudam a estabelecer uma ordem total ou parcial dos eventos sem a necessidade de sincronização do relógio físico. Desta forma é possível gerir o acesso a secções criticas por parte de vários threads, grantindo que apenas um acessa a SC num dado momento. No entanto, a implementação de relógios lógicos em exclusão mútua distribuída pode introduzir uma sobrecarga de comunicação significativa. Manter e trocar informações de estado entre processos para atualizar os relógios lógicos pode aumentar a quantidade de mensagens trocadas.

## 4

O sistema deve ser capaz de alocar recursos de maneira eficiente e escalar horizontalmente (adicionando mais máquinas físicas ou instâncias virtuais) para atender à quantidade de pedidos. Se os recursos são limitados, o sistema deve priorizar os pedidos, alocar recursos dinamicamente e garantir que os serviços críticos permaneçam operacionais. **(como resolver ???)**