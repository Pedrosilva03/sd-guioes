# Teste 2020 - Grupo I

## 1 Explique as vantagens e desvantagens relativas da utilização de formatos de texto (por oposição a formatos binários) para serialização de dados em sistemas distribuídos.

O formato de texto, em comparação com o formato binário, é mais legível e alterações no conteúdo não invalidam totalmente a sua descodificação. No entento este formato requer parsing e o tamnho é maior.

## 2 Defina transparência de acesso e explique em que medida é que a invocação remota (RPC) contribui para a obter.

A transparência de acesso consiste em ocultar ao usuário que determinados recursos do sistema distribuido podem ser acedidos. Um usuário não sabe se um recurso é local ou remoto, ou seja o sistema não tem que forncecer a localização dos recursos. As escrita de leitura de arquivos remotos ou locais é feita da mesma forma sem que hajam modificações nos programas. É desta forma que o RPC ajuda a cumprir a transparência de acesso. O RPC encapsula as rotinas de acesso e consulta como também efetua o controlo de concorrência. 

## 3 Identifique a principal dificuldade criada pela escala geográfica a aplicações cliente/servidor interativas e explique uma forma de a resolver.

Num sistema onde os clientes e os recursos se encontram afastados geográficamente, a distancia pode resultar em atrosos sentidos pelos clientes. Replicação de dados ou criação de caches são formas de atenuar este problema da distância e reduzir a latência.