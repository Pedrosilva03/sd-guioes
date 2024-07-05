# Época Especial 2023 - Grupo I

## 1

A estratégia de 1-thread-por-pedido é geralmente mais eficiente do que a estratégia de 1-thread-por-ligação, especialmente quando o número de ligações é bastante elevado. Na estratégia de 1-thread-por-ligação, cada conexão ativa com o servidor é gerida por uma thread separada. Isto pode rapidamente esgotar os recursos do sistema devido ao elevado número de threads ativas, resultando em overhead significativo na gestão de threads.

Na estratégia de 1-thread-por-pedido, o número de threads é proporcional à quantidade de pedidos feitos ao servidor, em vez de ser proporcional ao número de ligações. Se um cliente não fizer nenhum pedido ao servidor, não há necessidade de manter uma thread dedicada à sua conexão, o que resulta em uma utilização mais eficiente dos recursos do sistema.

Além disso, ao abrir threads por pedidos, o sistema só consome recursos quando há atividade, ou seja, quando há pedidos a serem processados. Isso permite uma gestão mais eficiente dos recursos, dado que cada pedido é tratado de forma isolada por uma thread dedicada. Este modelo também facilita a gestão de múltiplos pedidos do mesmo cliente, uma vez que cada pedido é tratado pela sua própria thread.

## 2

A principal dificuldade na serialização de estruturas de dados ligadas é representar as referências entre os vários elementos da estrutura. Por exemplo, numa lista ligada, indicar qual o elemento seguinte. Para ultrapassar esta dificuldade pode ser atribuido a cada nó da estrutura um identificador e serializar o conteúdo do nó juntamente com o identificador. Ao descirializar cada nó é possível reestabelecer a ordem da estrutura.

## 3

No protocolo de two-phase commit, numa primeira fase, um coordenador envia um pedido de preparação aos participantes envolvidos na transação. Os participantes preparam a transação, garantindo que podem realizar o commit sem falhar, e respondem ao coordenador que "estão prontos".

Na segunda fase (fase de commit), se todos os participantes responderem que estão prontos, o coordenador envia a mensagem de "commit" para todos. Os participantes, então, realizam o commit e confirmam ao coordenador que a transação foi concluída.

No entanto, se algum participante falhar depois de ter respondido que está pronto, este irá reiniciar e verificar o seu log de transações. Se o log indicar que o mesmo estava pronto para proceder com a transação, contacta o coordenador para saber qual o estado final (commit ou abort) e, assim, completar a transação de acordo com a decisão do coordenador.

O coordenador também pode falhar em qualquer ponto do processo. Se o coordenador falhar antes de enviar a mensagem de commit, ao reiniciar, ele verificará o seu log de transações. Se o log mostrar que todos os participantes estavam prontos, ele pode decidir prosseguir com a transação enviando a mensagem de commit. Caso contrário, ele pode optar por abortar a transação e notificar os participantes.

## 4

Num serviço distribuído por vários servidores que trabalham todos com a mesma fila de espera, o principal problema será a concorrência para alterar o estado da fila de espera. Um problema comum em sistemas distribuídos é garantir a consistência e integridade dos dados partilhados, evitando condições de corrida e garantindo que todos os servidores tenham uma visão consistente da fila.

Uma solução típica para este problema é a utilização de um protocolo de coordenação, onde um servidor é designado como coordenador responsável por gerir a fila de espera. O algoritmo Bully pode ser utilizado para eleger um coordenador entre os servidores, especialmente em situações de falha, garantindo que um novo coordenador seja eleito se o atual falhar. 

Além da eleição do coordenador, é necessário garantir que as operações na fila sejam realizadas de maneira consistente. Isso pode ser alcançado utilizando mecanismos de sincronização e coordenação, como o paxos.