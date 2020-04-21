# Planejamento de testes:

## 1. Revisao de documentacao e novas Stories para o time:
-Nesse estagio, o QA revisa a documentacao em busca dos principais pontos abaixo:

Clareza;
Consistencia;
Redundancia;


## 2. Criacao de plano de Testes
-Definicao de abordagem de testes (Load, Automation, Manual, etc)
-Criacao de massa de dados (Se aplicavel)
-Comecar a criar os testes automatizados;*

*A depender do framework, ja podemos nessa fase adiantar a criacao dos story files;

## 3. Execucao
-Execucao de testes manuais;
-Criar ou finalizar os testes automatizados e incluir os mesmos no pipeline;
-Caso sejam encontrados defeitos, enviar para o desenvolver para que sejam aplicadas as devidas correcoes;

## 4. Regressao
-Quando houver uma nova release da aplicacao, executar todos os testes da regressao;
-Caso sejam encontrados bugs nesse estagio, o QA tem a responsabilidade de entender qual a severidade/impacto
e em conjunto com o PO, definir qual a prioridade para a correcao do mesmo;


## Abaixo, segue uma relacao das principais ferramentas/metodologias utilizadas:

Scrum:
Entrega incremental de valor ao negocio. Entregas entre 1 e 4 semanas a depender
da demanda do time;

Azure DevOps:
Gerenciamento de Stories e Bugs atraves de um board Kanban;
Pipeline CICD;
Gerenciamento do Plano de Testes (Criacao de Test Cases e Execucao);
Repositorio de Codigo;

Comunicacao entre equipes:
Hangouts, Skype ou Slack;

Automacao:
Web (Java, Selenium, JBehave);
API (Java, JBehave, RestAssured or Spring);
Mobile (Java, Selenium, Appium);

Confluence Wiki:
Documentos e procedimentos;