Scenario: User should not be able to send a payload without token
Given I create a request without token to create a report for <userFullName>, <cpfNumber> and <dataNascimento>
Then I should receive a message <errorMessage>

Examples:
|userFullName		|cpfNumber		|dataNascimento	|errorMessage							|
|Felipe Pazinatto	|04492636900	|06/07/1992		|Token de autenticacao nao especificado	|


Scenario: User should receive an error message based on inconsistent data supplied
Given I create a request to create a report for <userFullName>, <cpfNumber> and <dataNascimento>
When I consult data in Policia Federal database
Then I should receive <errorMessage> message in the report

Examples:
|userFullName		|cpfNumber	|dataNascimento	|errorMessage																											|
|Felipe dos Santos	|04492636900|06/07/1992		|Nome diferente do cadastrado na Receita Federal.																		|
|Felipe Pazinatto	|04492636900|06/07/1993		|Data de nascimento informada esta divergente da constante na base de dados da Secretaria da Receita Federal do Brasil.	|
|Felipe Pazinatto	|28943231075|06/07/1992		|CPF nao encontrado na base de dados da Secretaria da Receita Federal do Brasil.										|


Scenario: User should receive an error message for not supplying all parameters
Given I create a request to create a report for <userFullName>, <cpfNumber> and <dataNascimento>
Then I should receive a message <errorMessage>

Examples:
|userFullName		|cpfNumber	|dataNascimento	|errorMessage																										|
| 					|			|				|E necessario enviar ao menos um parametro para criacao do relatorio.												|
| 					|04492636900|06/07/1992		|Error: A matriz "Relatorio padrao PF" requer que o campo "Nome completo" ("parametros.cpf_nome") seja fornecido.	|
|Felipe Pazinatto 	|04492636900|				|ValidationError: child "cpf_data_de_nascimento" fails because ["cpf_data_de_nascimento" is not allowed to be empty]|
|Felipe Pazinatto 	|			|06/07/1992		|ValidationError: child "cpf_numero" fails because ["cpf_numero" is not allowed to be empty]						|


Scenario: User should receive report based on valid data informed
Given I create a request to create a report for <userFullName>, <cpfNumber> and <dataNascimento>
When I consult data in Policia Federal database
Then the report should present correct data

Examples:
|userFullName		|cpfNumber	|dataNascimento	|
|Felipe Pazinatto	|04492636900|06/07/1992		|