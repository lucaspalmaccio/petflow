Projeto Clinicar - TCC
Este documento contém as instruções para executar o projeto completo (Backend, Frontend e Banco de Dados) utilizando Docker.

Pré-requisitos
O único software necessário para executar este projeto é o Docker Desktop. Nenhum outro programa (Java, Node.js, PostgreSQL) precisa ser instalado na máquina.

Faça o download do Docker Desktop aqui

Após a instalação, certifique-se de que o Docker Desktop esteja em execução.

Como Executar o Projeto
Siga estes passos simples para colocar toda a aplicação no ar.

Passo 1: Obtenha o Código-Fonte
Clone ou faça o download deste repositório para a sua máquina. Se estiver usando Git, use o comando:

git clone [https://github.com/lucaspalmaccio/clinicaroficial.git](https://github.com/lucaspalmaccio/clinicaroficial.git)

Depois, navegue para a pasta do projeto:

cd clinicar

Passo 2: Execute o Docker Compose
Com o Docker Desktop rodando e o terminal aberto na pasta raiz do projeto (clinicar), execute o seguinte comando:

docker-compose up --build -d

O que este comando faz?

docker-compose up: Inicia todos os serviços definidos no arquivo docker-compose.yml.

--build: Constrói as imagens do backend e do frontend do zero. Isso garante que qualquer alteração no código seja aplicada.

-d: Roda os contêineres em modo "detached" (em segundo plano), liberando seu terminal.

A primeira vez que este comando for executado, ele pode demorar alguns minutos para baixar as imagens e construir os projetos.

Passo 3: Acesse a Aplicação
Após o comando terminar, aguarde cerca de um minuto para que todos os serviços (especialmente o backend) iniciem completamente.

Abra seu navegador de internet e acesse o seguinte endereço:

http://localhost

Você será recebido pela tela de login da aplicação. A partir daí, você pode se cadastrar, fazer login e utilizar todas as funcionalidades.

Como Parar a Aplicação
Quando terminar de usar, você pode parar todos os contêineres com um único comando. No terminal,s na mesma pasta do projeto, execute:

docker-compose down

Isso irá parar e remover os contêineres, mas manterá os dados do banco de dados salvos para a próxima vez que você iniciar.

Para parar e apagar todos os dados (incluindo o banco de dados), use:

docker-compose down -v
