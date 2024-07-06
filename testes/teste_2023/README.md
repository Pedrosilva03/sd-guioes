# Teste 2022 - Grupo I

## Escolha múltipla 

1. a) e d)

2. a)

3. a) e b)

4. a), b) e c)

## Desenvolvimento

Quando um nó detecta que o líder atual falhou, o algoritmo Bully inicia uma eleição enviando mensagens de eleição a todos os nós com identificadores mais altos que o seu próprio. Eventualmente será elegido um novo lider. Este algoritmo é útil para garantir a continuidade da operação de um sistema distribuído replicado após a falha de um nó líder. Este permite a rápida eleição de um novo líder, mantendo a consistência e a coordenação entre os nós restantes. Em sistemas com um grande número de nós, o algoritmo bully pode gerar um grande volume de mensagens, especialmente durante falhas múltiplas ou em sistemas com alta frequência de falhas. Isto pode resultar num desempenho degradado e aumento da latência.
