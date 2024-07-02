# Época Especial 2023 - Grupo I

## 1

A estratégia 1-thread-por-pedido é geralmente mais eficiente. Quando o número de ligações é bastante elevado, a estratégia 1-thread-por-ligação irá esgotar rapidamente os recursos do sistema dado o elevado número de threads ativas. Já na estratégia 1-thread-por-pedido, o número de threads depende da quantidade de pedidos feitos ao servidor. Se um cliente não realizou nenhum pedido ao servidor, não faz sentido que exista um thread dedicado à sua conexão com o servidor a consumir recursos. Abrir threads por pedidos resulta num consumo mais eficiente de recursos do sistema, visto que um cliente só consome recursos se fizer pedidos ao servidor. Desta forma é também possível gerir de forma mais eficiente vários pedidos vindos do mesmo cliente, visto que cada um tem uma thread dedicada.

## 2

A principal dificuldade na serialização de estruturas de dados ligadas é representar as referências entre os vários elementos da estrutura. Por exemplo, numa lista ligada, indicar qual o elemento seguinte. Para ultrapassar esta dificuldade pode ser atribuido a cada nó da estrutura um identificador e serializar o conteúdo do nó juntamente com o identificador. Ao descirializar cada nó é possível reestabelecer a ordem da estrutura.

## 3

No two-phase commit, numa primeira fase, um coordenador envia um pedido aos participantes envolvidos na transação. Estes vão preparar a transação e notificar o coordenador que "estão prontos". Numa segunda fase, o coordenador envia a mensagem de "commit". No entanto um participante pode falhar nesta fase. Neste caso o participante irá reiniciar e ver que estava pronto para proceder com a transação (através de logs ??). O coordenador também pode falhar antes de enviar a mensagem de commit. Neste caso, este irá reparar que tinha uma transação pendente e prosseguir com a mesma.

## 4

Num serviço distribuido por vários servidores que trabalham todos com a mesma fila de espera, o principal problema será os servidores concorrerem para alterar o estado da fila de espera. O algoritmo Bully poderia ser uma solução para manter a integridade da fila. Deve ser elegido um servidor (coordenador) que é responsável pela alteração do estado da fila. Este algoritmo também tolera falhas no coordenador. Se este falhar poderá ser elegido um novo.