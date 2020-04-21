## Instrucoes para rodar o projeto:
-Importar o projeto desafioQa como Maven no Eclipse;

<br/>

-Baixar o JBehave, conforme explicado no topico "How to install the Eclipse JBehave Plug-In" nesse link:<br/>
-https://testguild.com/jbehave-bdd-plugin/<br/><br/>
-Rodar a classe RestStoryRunner como Junit para executar os scenarios do arquivo Reports.story, e passar o token pela vm arguments como parametro:<br/>
-Dtoken="TOKEN"<br/>

O scenario conhecido e o ultimo do Story file;
<br/>

## Escolha das ferramentas:
Os seguintes foram os pontos que justificam as ferramentas escolhidas:<br/>
-Java: Linguagem popular e amplamenta utilizada;<br/>
-JBehave: Apresentacao facil e bastante amigavel atraves dos Story files, ate mesmo para pessoas nao tecnicas. Possibilidade de parametrizar
scenarios diferentes com base nos Example tables;


## Observacoes:
-Caso seja necessario, alterar o timeout do story file (useStoryTimeoutInSecs) dentro da classe RestStoryRunner. O Valor esta em segundos,
coloquei um valor alto, pois estava demorando alguns minutos pra retornar a consulta;<br/>
-Aumentar o counter while do method RestUtilitiesMethods.runGetRequest para aumentar o numero de vezes que sao enviadas o GET para buscar
o relatorio;<br/>
-O report gerado pelo JBehave apos cada execucao fica localizado no caminho \\desafioQa\target\jbehave\view\index.html;


## Melhorias:
-Colocar as mensagens de erro que estao nos Examples das stories em arquivos de property;<br/>
-Validar tamanho dos campos aceitos. Exemplo, Boundary testing;<br/>
-Deixar os steps em portugues;
