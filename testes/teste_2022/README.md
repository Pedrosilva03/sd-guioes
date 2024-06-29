# Teste 2022 - Grupo I

## Escolha múltipla 

1. c)

2. c) e d)

3. b)

4. a)

## Desenvolvimento

Na situação descrita, o problema mais importante é manter um consenso sobre o estado do sistema entre os nós intervenientes. Uma forma de manter este consenso é o two phase commit. Um participante que queira fazer uma transação envia uma mensagem aos outros a pedir a aprovação da mesma. Caso todos o nós aprovem a transação, o coordenador envia uma mensagem de commit. Este método permite o consenso mas poderá ser pouco eficiente em termos de latência.