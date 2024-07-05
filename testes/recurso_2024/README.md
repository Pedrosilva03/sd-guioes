# Recurso 2024 - Grupo I

## 1

O algoritmo de Peterson é uma técnica para assegurar a exclusão mútua entre dois processos ou threads. Ele funciona bem para dois threads, utilizando duas variáveis principais: flag, para indicar que um thread deseja entrar na seção crítica, e turn, para indicar de quem é a vez de entrar na seção crítica.

Para mais de dois threads, o algoritmo de Peterson não é diretamente aplicável na sua forma original, pois foi desenhado apenas para dois threads. No algoritmo de Peterson, no caso de existirem dois threads, um deles tem que baixar a flag para o outro poder entrar na SC. No caso de existirem três threads, dois threas irão continuar com a flag levantada. Isto resulta numa situação de deadlock, logo o  programa bloqueia.

## 2

A migração de código consiste em passar o processamento de certas tarefas do servidor para o cliente. Isto terá impacto na carga do servidor. Visto que parte do processamento passa a ser feito no cliente, o servidor irá ter menos tarefas para realizar. A migração de código pode tornar o sistema mais escalável. Esta redução da carga do servidor pode permitir que exista um número maior de utilizadores no sistema, aumentando assim a sua escalabilidade. O facto de parte do processamento ser passado para o cliente resulta também na redução da latência experênciada pelo mesmo.

## 3

Relógios lógicos, como os vetoriais ou de Lamport, fornecem uma maneira eficiente de coordenar e ordenar eventos em sistemas distribuídos. Estes ajudam a estabelecer uma ordem total ou parcial dos eventos sem a necessidade de sincronização do relógio físico. Desta forma é possível gerir o acesso a secções criticas por parte de vários threads, grantindo que apenas um acessa a SC num dado momento. No entanto, a implementação de relógios lógicos em exclusão mútua distribuída pode introduzir uma sobrecarga de comunicação significativa. Manter e trocar informações de estado entre processos para atualizar os relógios lógicos pode aumentar a quantidade de mensagens trocadas.

## 4

O principal problema de sistemas distribuídos para garantir que este serviço esteja sempre disponível, mesmo quando as máquinas físicas podem falhar, é a tolerância a falhas. Uma solução eficaz para este problema é a utilização de algoritmos de consenso, como o **Paxos**, para manter a consistência e disponibilidade do sistema. O estado do sistema é replicado em múltiplos nós, o que permite que o estado do sistema seja consistentemente mantido. Quando uma operação crítica precisa ser realizada, um processo de votação entre os nós é iniciado. Existe um nó lider que coordena a atividade. Em caso de falha, este método permite que seja elegido um novo lider. Desta forma, o paxos permite que as falhas não comprometam totalmente a disponibilidade do serviço.