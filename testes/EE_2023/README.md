# Época Especial 2023 - Grupo I

## 1

A **1-thread-por-ligação**, sempre que o cliente estabelece uma ligação com um servidor essa sessão vai ser executada num thread em separado. Dessa forma,  pedidos de clientes diferentes podem ser executados em simultâneo.

Já a **1-thread-por-pedido** permite também vários clientes, porém estes podem ter vários pedidos a ser executados em simultâneo. 

De um ponto de vista de eficiência, o 1-thread-por-pedido tem um custo adicional, devido a ser preciso criar um um novo thread por casa pedido, porém este permite também executar mais pedidos em simultâneo, relativamente ao 1-thread-por-ligação, que apenas permite um pedido por cliente.

## 2

A principal dificuldade na serialização de estruturas de dados ligadas é representar as referências entre os vários elementos da estrutura. Por exemplo, numa lista ligada, indicar qual o elemento seguinte. Para ultrapassar esta dificuldade pode ser atribuido a cada nó da estrutura um identificador e serializar o conteúdo do nó juntamente com o identificador. Ao descirializar cada nó é possível reestabelecer a ordem da estrutura.

## 3

No protocolo de two-phase commit, numa primeira fase, um coordenador envia um pedido de preparação aos participantes envolvidos na transação. Os participantes preparam a transação, garantindo que podem realizar o commit sem falhar, e respondem ao coordenador que "estão prontos".

Na segunda fase (fase de commit), se todos os participantes responderem que estão prontos, o coordenador envia a mensagem de "commit" para todos. Os participantes, então, realizam o commit e confirmam ao coordenador que a transação foi concluída.

No entanto, se algum participante falhar depois de ter respondido que está pronto, este irá reiniciar e verificar o seu log de transações. Se o log indicar que o mesmo estava pronto para proceder com a transação, contacta o coordenador para saber qual o estado final (commit ou abort) e, assim, completar a transação de acordo com a decisão do coordenador.

O coordenador também pode falhar em qualquer ponto do processo. Se o coordenador falhar antes de enviar a mensagem de commit, ao reiniciar, ele verificará o seu log de transações. Se o log mostrar que todos os participantes estavam prontos, ele pode decidir prosseguir com a transação enviando a mensagem de commit. Caso contrário, ele pode optar por abortar a transação e notificar os participantes.

## 4

Num serviço distribuído por vários servidores que trabalham todos com a mesma fila de espera, o principal problema será a concorrência para alterar o estado da fila de espera. Um problema comum em sistemas distribuídos é garantir a consistência e integridade dos dados partilhados, evitando condições de corrida e garantindo que todos os servidores tenham uma visão consistente da fila. 

Uma solução adequada a para esta situação seria implementar o paxos. Neste método, o estado do sistema é replicado pelos vários nós. Existe ainda a eleição de um nó (líder) que irá coordenar a atividade no sistema. Decisões são tomadas através de votações entre os nós. Desta forma, todos o nós têm a mesma visão sobre o estado do sistema, visto que existe um acordo no que toca ao estado atual e às mudanças a realizar no mesmo. O paxos ainda tolera falhas no coordenador através da eleição de um novo coordenador, logo, o funcionamento do sistema não é comprometido nesta situação.