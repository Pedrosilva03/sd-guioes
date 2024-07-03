# Recurso 2024 - Grupo I

## 1

O algoritmo de Peterson é uma técnica para assegurar a exclusão mútua entre dois processos ou threads. No entanto, ele não é diretamente aplicável a mais de dois threads. O algoritmo de Peterson para dois threads utiliza duas variáveis principais. Tomemos com exemplo o algoritmo de peterson a funcionar com 3 threads. Cada um destes threads quer entrar na secção critica. Quando cada um destes threads quer entrar na secção critica flag[i] == 1 (i<3), logo cada thread vai ficar à espera que os outros mudem a sua flag. Estamos numa situação de deadlock, ou seja, o programa bloqueia.

## 2

A migração de código consiste em passar o processamento de certas tarefas do servidor para o cliente. Isto terá impacto na carga do servidor. Visto que parte do processamento passa a ser feito no cliente, o servidor irá ter menos tarefas para realizar. A migração de código pode tornar o sistema mais escalável. Esta redução da carga do servidor pode permitir que exista um número maior de utilizadores no sistema, aumentando assim a sua escalabilidade. O facto de parte do processamento ser passado para o cliente resulta também na redução da latência experênciada pelo mesmo.

## 3

Relógios lógicos, como os vetoriais ou de Lamport, fornecem uma maneira eficiente de coordenar e ordenar eventos em sistemas distribuídos. Estes ajudam a estabelecer uma ordem total ou parcial dos eventos sem a necessidade de sincronização do relógio físico. Desta forma é possível gerir o acesso a secções criticas por parte de vários threads, grantindo que apenas um acessa a SC num dado momento. No entanto, a implementação de relógios lógicos em exclusão mútua distribuída pode introduzir uma sobrecarga de comunicação significativa. Manter e trocar informações de estado entre processos para atualizar os relógios lógicos pode aumentar a quantidade de mensagens trocadas.

## 4

O principal problema de sistemas distribuídos para garantir que este serviço esteja sempre disponível, mesmo quando as máquinas físicas podem falhar, é a tolerância a falhas. Uma solução eficaz para este problema é a utilização de algoritmos de consenso, como o **Paxos**, para manter a consistência e disponibilidade do sistema. O Paxos é um protocolo de consenso que pode garantir que mesmo que algumas das máquinas físicas falhem, o sistema ainda pode continuar a funcionar corretamente. Isto é alcançado através de um processo de votação entre os nós restantes para decidir o estado atual e continuar operação do serviço sem interrupções significativas.