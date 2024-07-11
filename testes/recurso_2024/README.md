# Recurso 2024 - Grupo I

## 1

O algoritmo de Peterson foi desenvolvido para resolver o problema da exclusão mútua quando temos duas threads. Neste, quando dois threads chegam mais ou menos ao mesmo tempo, à secção crítica, ambos erguem a sua bandeira, indicando que querem aceder à secção crítica, e o que chegar depois fica como vítima, só podendo avançar quando o outro baixar a sua bandeira. Num ambiente multi-thread apenas o último thread a chegar vai ficar como vítima, fazendo com que tenhamos vários threads ao mesmo tempo, dentro da secção crítica. Desta forma, o programa **não vai bloquear, mas não será capaz de garantir a exclusão mútua**.

## 2

A migração de código consiste em passar o processamento de certas tarefas do servidor para o cliente. Isto terá impacto na carga do servidor. Visto que parte do processamento passa a ser feito no cliente, o servidor irá ter menos tarefas para realizar. A migração de código pode tornar o sistema mais escalável. Esta redução da carga do servidor pode permitir que exista um número maior de utilizadores no sistema, aumentando assim a sua escalabilidade. O facto de parte do processamento ser passado para o cliente resulta também na redução da latência experênciada pelo mesmo.

## 3

O algoritmo distribuído de exclusão mútua baseado em relógios lógicos fornece um conjunto ordenado de eventos para garantir a exclusão mútua sem necessitar de um relógio físico sincronizado entres os vários participantes. A principal desvantagem é a sobrecarga de comunicação, uma vez que este algoritmo parte do princípio de que há uma troca constante de mensagens e o relógio lógico de um dado participante pode ficar bloqueado até receber uma mensagem.

## 4

O principal problema de sistemas distribuídos para garantir que este serviço esteja sempre disponível, mesmo quando as máquinas físicas podem falhar, é a tolerância a falhas. Uma solução eficaz para este problema é a utilização de algoritmos de consenso, como o **Paxos**, para manter a consistência e disponibilidade do sistema. O estado do sistema é replicado em múltiplos nós, o que permite que o estado do sistema seja consistentemente mantido. Quando uma operação crítica precisa ser realizada, um processo de votação entre os nós é iniciado. Existe um nó lider que coordena a atividade. Em caso de falha, este método permite que seja elegido um novo lider. Desta forma, o paxos permite que as falhas não comprometam totalmente a disponibilidade do serviço.