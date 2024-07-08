# Exame de 15 de Julho de 2023

## Grupo I

### 1.
- De um ponto de vista de eficiência, utilizar uma thread por pedido é mais eficiente por diversos motivos:
- - Visto que pedidos acontecem de forma mais rápida do que ligações, utilizar uma thread para cada pedido minimiza o número de threads ativas.
- - Para além disso, permite que as threads possam ser reutilizadas através, por exemplo, de uma pool de threads.
- - Utilizar uma thread para cada pedido permite que um cliente possa fazer vários pedidos ao mesmo tempo sem que tenha que esperar que pedidos anteriores terminem.
- - Permite alocar recursos de forma mais eficiente. Por exemplo se um cliente fica algum tempo sem fazer pedidos, recursos que seriam usados para ele podem ser usados para outros clientes.
- No caso de uma thread por ligação:
- - A sua implementação é mais simples
- - Gasta mais recursos, visto que, sempre que haja uma conexão, vai existir uma thread sempre ativa, o que pode causar problemas se houverem muitos clientes conectados.
- - Leva à utilização de recursos desnecessariamente, visto que se um cliente estiver conectado em estado "idle" (sem fazer nenhum pedido), estará a ser usada uma thread para ele de qualquer das formas.

### 2.
O problema na serialização de estruturas ligadas é não só guardar os valores dos nós mas também guardar os apontadores para os próximos nós. Se estes não forem guardados corretamente, pode levar a erros na máquina de destino (cópias de dados que deviam ter a mesma referência por exemplo). Para ultrapassar este problema utiliza-se um mapa auxiliar que guarda as referências para os objetos. Isto permite que caso uma estrutura de dados apareça duas vezes, não sejam criadas várias cópias, mas sim seja guardada uma referência de que os apontadores se referem ao mesmo objeto.

### 3.
No protocolo 2PC, quando o commit chega à segunda fase, isso significa que todos os participantes reportaram ao coordenador que estavam prontos para efetuar a transação. Porém, se um participante falhar na segunda fase, este pode reiniciar e pedir ao coordenador o estado da transação para que assim possa continuar. Isto permite que o participante que falhou possa recuperar o seu estado e acompanhar novamente o processo.

### 4.
O problema principal deste cenário é a sincronização e a integridade dos dados entre todos os servidores. Para além disso é necessário garantir que o sistema funciona mesmo que alguns servidores falhem. Uma solução interessante é eleger um líder que vai gerir a fila de espera. Este líder comunica aos outros servidores qualquer alteração na fila de espera. No caso do servidor líder falhar, pode existir um sistema que automaticamente elege um novo líder. Isto funciona bem se a fila de espera estiver bem sincronizada, assim o novo servidor líder pode continuar o trabalho do outro que falhou até que volte.