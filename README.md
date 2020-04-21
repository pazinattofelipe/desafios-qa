## Instrucoes para rodar o projeto:
-Importar o projeto desafioQa como Maven no Eclipse;
-Baixar o JBehave, conforme explicado no topico "How to install the Eclipse JBehave Plug-In" nesse link:
-https://testguild.com/jbehave-bdd-plugin/

-Rodar a classe RestStoryRunner como Junit para executar os scenarios do arquivo Reports.story, e passar o token pela vm arguments como parametro:
-Dtoken="********-****-****-****-************"

O scenario conhecido e o ultimo do Story file;


## Escolha das ferramentas:
Os seguintes foram os pontos que justificam as ferramentas escolhidas:
-Java: Linguagem popular e amplamenta utilizada;
-JBehave: Apresentacao facil e bastante amigavel atraves dos Story files, ate mesmo para pessoas nao tecnicas. Possibilidade de parametrizar scenarios
diferentes com base nos Example tables;


## Observacoes:
-Caso seja necessario, alterar o timeout do story file (useStoryTimeoutInSecs) dentro da classe RestStoryRunner. O Valor esta em segundos, coloquei um
valor alto, pois estava demorando alguns minutos pra retornar a consulta;
-Aumentar o counter while do method RestUtilitiesMethods.runGetRequest para aumentar o numero de vezes que sao enviadas o GET para buscar o relatorio;
-O report gerado pelo JBehave apos cada execucao fica localizado no caminho <PROJECT>\target\jbehave\view\index.html;


## Melhorias:
-Colocar as mensagens de erro que estao nos Examples das stories em arquivos de property;
-Validar tamanho dos campos aceitos. Exemplo, Boundary testing;
-Deixar os steps em portugues;